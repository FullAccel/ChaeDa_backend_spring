package Chaeda_spring.domain.statistics.service;

import Chaeda_spring.domain.Problem.math.MathProblemType;
import Chaeda_spring.domain.Problem.math.MathProblemTypeRepository;
import Chaeda_spring.domain.member.entity.Member;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.statistics.dto.SolvedCountsByMonthResponse;
import Chaeda_spring.domain.statistics.dto.StatisticsBySubconceptResponse;
import Chaeda_spring.domain.statistics.dto.WrongCountWithSubconceptResponse;
import Chaeda_spring.domain.statistics.entity.problem_type_statistics.*;
import Chaeda_spring.domain.statistics.entity.solvedNum.*;
import Chaeda_spring.global.constant.Chapter;
import Chaeda_spring.global.constant.SubConcept;
import Chaeda_spring.global.constant.Subject;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(StatisticsService.class);
    private final MathProblemTypeRepository mathProblemTypeRepository;
    private final SolvedNumForDayRepository solvedNumForDayRepository;
    private final SolvedNumForWeekRepository solvedNumForWeekRepository;
    private final SolvedNumForMonthRepository solvedNumForMonthRepository;
    private final SubconceptStatisticsForWeekRepository subconceptStatisticsForWeekRepository;
    private final SubconceptStatisticsForMonthRepository subconceptStatisticsForMonthRepository;

    private final AccumulatedStatisticsForChapterRepository accumulatedStatisticsForChapterRepository;
    private final AccumulatedStatisticsForSubconceptRepository accumulatedStatisticsForSubconceptRepository;

    /**
     * 지정된 날짜부터 7일 동안 각 날짜에 대해 해결한 문제 수를 검색합니다.
     *
     * @param member 해결된 개수를 검색할 멤버입니다.
     * @param date   카운팅을 시작할 날짜
     * @return 7일 이내의 각 날짜에 대한 해결된 문제 수가 포함된 응답입니다.
     */
    public Map<LocalDate, Integer> getSolvedCountByDate(Member member, LocalDate date) {
        LocalDate sevenDaysAgo = date.minusDays(6);
        log.info("sevenDaysAgo : {}, {}", date, sevenDaysAgo);
        log.info("student : {}", member.getId());
        List<SolvedNumForDay> solvedNums = solvedNumForDayRepository.find7DaysByTodayDateBetweenAndStudent(sevenDaysAgo, date, (Student) member);
        Map<LocalDate, Integer> solvedNumMap = solvedNums.stream()
                .collect(Collectors.toMap(SolvedNumForDay::getTodayDate, SolvedNumForDay::getSolvedNum));

        solvedNums.stream()
                .forEach(t -> log.info("solvenum : {}", t));
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
        LocalDate eightWeeksAgo = thisWeek.minusWeeks(7);

        List<SolvedNumForWeek> solvedNums = solvedNumForWeekRepository.findForWeeksBetweenStartDateAndEndDateAndStudent(eightWeeksAgo, thisWeek, (Student) member);

        Map<LocalDate, Integer> solvedNumMap = solvedNums.stream()
                .collect(Collectors.toMap(SolvedNumForWeek::getStartOfWeek, SolvedNumForWeek::getSolvedNum));

        return solvedNumMap;
    }

    public Map<String, Integer> getSolvedCountByMonth(Member member) {
        LocalDate today = LocalDate.now();
        LocalDate todayMonth = LocalDate.now().withDayOfMonth(1);

        LocalDate sixMonthsAgo = today.minusMonths(5);

        List<SolvedNumForMonth> solvedNums = solvedNumForMonthRepository.findForMonthsBetweenDatesAndStudent(sixMonthsAgo, todayMonth, (Student) member);

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

    public StatisticsBySubconceptResponse getWeeklyStatisticsBySubConcept(Member member, SubConcept subConcept) {

        MathProblemType type = mathProblemTypeRepository.findBySubConcept(subConcept);

        LocalDate today = LocalDate.now();
        LocalDate thisWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        SubconceptStatisticsForWeek stats = subconceptStatisticsForWeekRepository.findByStartOfWeekAndStudentAndType(thisWeek, (Student) member, type);

        return StatisticsBySubconceptResponse.from(subConcept, stats);
    }

    public StatisticsBySubconceptResponse getMonthlyStatisticsBySubConcept(Member member, SubConcept subConcept) {

        MathProblemType type = mathProblemTypeRepository.findBySubConcept(subConcept);
        LocalDate thisMonth = LocalDate.now().withDayOfMonth(1);

        SubconceptStatisticsForMonth stats = subconceptStatisticsForMonthRepository.findByTargetMonthAndStudentAndType(thisMonth, (Student) member, type);

        return StatisticsBySubconceptResponse.from(subConcept, stats);
    }

    public StatisticsBySubconceptResponse getAccumulatedStatisticsBySubConcept(Member member, SubConcept subConcept) {

        MathProblemType type = mathProblemTypeRepository.findBySubConcept(subConcept);

        AccumulatedStatisticsForSubconcept stats = accumulatedStatisticsForSubconceptRepository.findByStudentAndType((Student) member, type);

        return StatisticsBySubconceptResponse.from(subConcept, stats);
    }

    public List<StatisticsBySubconceptResponse> getAccumulatedStatisticsBySubConceptList(Member member, Chapter chapter) {

        return mathProblemTypeRepository.findAllByChapter(chapter).stream()
                .map((type) -> accumulatedStatisticsForSubconceptRepository.findByStudentAndType((Student) member, type))
                .map((stats) -> StatisticsBySubconceptResponse.from(stats.getType().getSubConcept(), stats))
                .collect(Collectors.toList());
    }
}
