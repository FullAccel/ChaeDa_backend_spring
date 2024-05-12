package Chaeda_spring.domain.statistics;

import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.statistics.entity.solvedNum.*;
import Chaeda_spring.global.utils.MemberUtils;
import Chaeda_spring.global.utils.SpringApplicationContext;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Component
@RequiredArgsConstructor
public class SolvedNumForDayListener {

    private final MemberUtils memberUtils;

    @PreUpdate
    @PrePersist
    public void updateSolvedNum(SolvedNumForDay solvedNumForDay) {

        Student student = (Student) memberUtils.getCurrentMember();
        SolvedNumForWeekRepository weekRepository = getWeekRepository();
        SolvedNumForMonthRepository monthRepository = getMonthRepository();

        // logic for week
        SolvedNumForWeek weekStatistics = weekRepository.findByStartOfWeekAndStudent(LocalDate.now().
                with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)), student);
        if (weekStatistics != null) {
            // 주간 풀이 횟수 통계가 있다면 업데이트합니다.
            weekStatistics.increaseSolvedNum(solvedNumForDay.getSolvedNum());
            weekRepository.save(weekStatistics);
        } else {
            // 주간 풀이 횟수 통계가 없다면 생성합니다.
            SolvedNumForWeek newWeekStatistics = SolvedNumForWeek.builder()
                    .startOfWeek(LocalDate.now().with(DayOfWeek.MONDAY))
                    .build();
            weekRepository.save(newWeekStatistics);
        }

        // logic for month
        SolvedNumForMonth monthStatistics = monthRepository.findByMonthDateAndStudent(LocalDate.now().withDayOfMonth(1), student);
        if (monthStatistics != null) {
            // 월간 풀이 횟수 통계가 있다면 업데이트합니다.
            monthStatistics.increaseSolvedNum(solvedNumForDay.getSolvedNum());
            monthRepository.save(monthStatistics);

        } else {
            // 월간 풀이 횟수 통계가 없다면 업데이트합니다.
            SolvedNumForMonth newMonthStatistics = SolvedNumForMonth.builder()
                    .monthDate(LocalDate.now().withDayOfMonth(1))
                    .build();
            monthRepository.save(newMonthStatistics);
        }
    }

    private SolvedNumForWeekRepository getWeekRepository() {
        return SpringApplicationContext.getBean("solvedNumForWeekRepository", SolvedNumForWeekRepository.class);
    }

    private SolvedNumForMonthRepository getMonthRepository() {
        return SpringApplicationContext.getBean("solvedNumForMonthRepository", SolvedNumForMonthRepository.class);
    }
}
