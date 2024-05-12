package Chaeda_spring.domain.statistics.service;

import Chaeda_spring.domain.member.entity.Member;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.statistics.dto.SolvedCountsByMonthResponse;
import Chaeda_spring.domain.statistics.dto.WrongCountWithSubconceptResponse;
import Chaeda_spring.domain.statistics.entity.problem_type_statistics.AccumulatedStatisticsForChapterRepository;
import Chaeda_spring.domain.statistics.entity.problem_type_statistics.SubconceptStatisticsForMonthRepository;
import Chaeda_spring.domain.statistics.entity.problem_type_statistics.SubconceptStatisticsForWeekRepository;
import Chaeda_spring.domain.statistics.entity.solvedNum.*;
import Chaeda_spring.global.constant.Chapter;
import Chaeda_spring.global.constant.SubConcept;
import Chaeda_spring.global.constant.Subject;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final SolvedNumForDayRepository solvedNumForDayRepository;
    private final SolvedNumForWeekRepository solvedNumForWeekRepository;
    private final SolvedNumForMonthRepository solvedNumForMonthRepository;
    private final SubconceptStatisticsForWeekRepository subconceptStatisticsForWeekRepository;
    private final SubconceptStatisticsForMonthRepository subconceptStatisticsForMonthRepository;
    private final AccumulatedStatisticsForChapterRepository accumulatedStatisticsForChapterRepository;

    /**
     * 지정된 날짜부터 7일 동안 각 날짜에 대해 해결한 문제 수를 검색합니다.
     *
     * @param member 해결된 개수를 검색할 멤버입니다.
     * @param date   카운팅을 시작할 날짜
     * @return 7일 이내의 각 날짜에 대한 해결된 문제 수가 포함된 응답입니다.
     */
    public Map<LocalDate, Integer> getSolvedCountByDate(Member member, LocalDate date) {
        LocalDate sevenDaysAgo = date.minusDays(7);
        List<SolvedNumForDay> solvedNums = solvedNumForDayRepository.findForDaysByTodayDateAndStudent(sevenDaysAgo, (Student) member);
        Map<LocalDate, Integer> solvedNumMap = solvedNums.stream()
                .collect(Collectors.toMap(SolvedNumForDay::getTodayDate, SolvedNumForDay::getSolvedNum));

        return solvedNumMap;
    }


    /**
     * Retrieves the count of solved problems by week for a member.
     *
     * @param member The member for which to retrieve the counts.
     * @return A SolvedCountByWeekResponse object containing a map of week start dates to the corresponding solved problem count.
     */
    public Map<LocalDate, Integer> getSolvedCountByWeek(Member member) {
        LocalDate today = LocalDate.now();
        LocalDate thisWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate eightWeeksAgo = thisWeek.minusWeeks(8);

        List<SolvedNumForWeek> solvedNums = solvedNumForWeekRepository.findForWeeksByStartOfWeekAndStudent(eightWeeksAgo, (Student) member);

        Map<LocalDate, Integer> solvedNumMap = solvedNums.stream()
                .collect(Collectors.toMap(SolvedNumForWeek::getStartOfWeek, SolvedNumForWeek::getSolvedNum));

        return solvedNumMap;
    }

    public Map<String, Integer> getSolvedCountByMonth(Member member) {
        LocalDate today = LocalDate.now();
        LocalDate sixMonthsAgo = today.minusMonths(6);

        List<SolvedNumForMonth> solvedNums = solvedNumForMonthRepository.findForMonthsByMonthDateAndStudent(sixMonthsAgo, (Student) member);

        Map<String, Integer> solvedNumMap = new LinkedHashMap<>();
        for (SolvedNumForMonth solvedNumForMonth : solvedNums) {
            String monthKey = solvedNumForMonth.getMonthDate().format(DateTimeFormatter.ofPattern("yyyy-MM"));
            solvedNumMap.put(monthKey, solvedNumForMonth.getSolvedNum());
        }

        return solvedNumMap;
    }

    public List<WrongCountWithSubconceptResponse> getTop10WrongCountWithConceptForWeek(LocalDate date, Member member) {
        Pageable topTen = PageRequest.of(0, 10);
        LocalDate startOfWeek = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        return subconceptStatisticsForWeekRepository.findTop10WrongCountByWeekAndStudent(startOfWeek, (Student) member, topTen)
                .stream()
                .map(WrongCountWithSubconceptResponse::of)
                .collect(Collectors.toList());
    }

    public List<SolvedCountsByMonthResponse> getTop10WrongCountWithConceptForMonth(Member member, LocalDate targetMonth) {
        Pageable topTen = PageRequest.of(0, 10);
        return subconceptStatisticsForMonthRepository.findTop10WrongCountByMonthAndStudent(targetMonth, (Student) member, topTen)
                .stream()
                .map(SolvedCountsByMonthResponse::of)
                .collect(Collectors.toList());
    }

    public List<Chapter> getChapterListBySubject(Subject subject) {
        return Subject.getChaptersBySubjectName(subject);
    }

    public List<SubConcept> getSubconceptListByChapter(Chapter chapter) {
        return Chapter.getSubConceptsByChapterName(chapter);
    }
}
