package Chaeda_spring.domain.submission.assignment.service;

import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.statistics.entity.solvedNum.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Service
@RequiredArgsConstructor
public class SolvedNumStatisticsUpdater {
    // 이곳에 repository 인스턴스를 정의합니다.
    private final SolvedNumForDayRepository solvedNumForDayRepository;

    private final SolvedNumForWeekRepository solvedNumForWeekRepository;

    private final SolvedNumForMonthRepository solvedNumForMonthRepository;

    public void updateSolvedNumForDay(int solvedNum, Student student) {
        LocalDate today = LocalDate.now();
        SolvedNumForDay solvedNumForDay = solvedNumForDayRepository.findByTodayDateAndStudent(today, student);
        if (solvedNumForDay == null) {
            solvedNumForDay = SolvedNumForDay.builder()
                    .todayDate(today)
                    .student(student)
                    .build();
        }
        solvedNumForDay.increaseSolvedNum(solvedNum);
        solvedNumForDayRepository.save(solvedNumForDay);
    }

    public void updateSolvedNumForWeek(int solvedNum, Student student) {
        SolvedNumForWeek weekStatistics = solvedNumForWeekRepository.
                findByStartOfWeekAndStudent(
                        LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)), student);
        if (weekStatistics == null) {
            weekStatistics = SolvedNumForWeek.builder()
                    .startOfWeek(LocalDate.now().with(DayOfWeek.MONDAY))
                    .student(student)
                    .build();
        }
        weekStatistics.increaseSolvedNum(solvedNum);
        solvedNumForWeekRepository.save(weekStatistics);
    }

    public void updateSolvedNumForMonth(int solvedNum, Student student) {
        SolvedNumForMonth monthStatistics = solvedNumForMonthRepository.findByMonthDateAndStudent(LocalDate.now().withDayOfMonth(1), student);
        if (monthStatistics == null) {
            monthStatistics = SolvedNumForMonth.builder()
                    .monthDate(LocalDate.now().withDayOfMonth(1))
                    .student(student)
                    .build();
        }
        monthStatistics.increaseSolvedNum(solvedNum);
        solvedNumForMonthRepository.save(monthStatistics);
    }
}
