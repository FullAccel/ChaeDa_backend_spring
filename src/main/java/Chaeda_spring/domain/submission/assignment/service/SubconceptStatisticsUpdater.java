package Chaeda_spring.domain.submission.assignment.service;

import Chaeda_spring.domain.Problem.math.MathProblemType;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.statistics.entity.problem_type_statistics.*;
import Chaeda_spring.global.constant.DifficultyLevel;
import Chaeda_spring.global.utils.MemberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class SubconceptStatisticsUpdater {

    private final SubconceptStatisticsForWeekRepository subconceptStatisticsForWeekRepository;
    private final SubconceptStatisticsForMonthRepository subconceptStatisticsForMonthRepository;
    private final MemberUtils memberUtils;

    public void updateSubconceptStatistics(
            MathProblemType mathProblemType,
            boolean isWrong,
            String problemNum,
            HashMap<String, DifficultyLevel> wrongProblemRecordMap
    ) {
        Student student = (Student) memberUtils.getCurrentMember();

        updateStatistics(
                SubconceptStatisticsForWeek.class,
                LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)),
                student,
                mathProblemType,
                isWrong,
                problemNum,
                wrongProblemRecordMap,
                subconceptStatisticsForWeekRepository
        );
        updateStatistics(
                SubconceptStatisticsForMonth.class,
                LocalDate.now().withDayOfMonth(1),
                student,
                mathProblemType,
                isWrong,
                problemNum,
                wrongProblemRecordMap,
                subconceptStatisticsForMonthRepository
        );
    }

    private <T extends Statistics> void updateStatistics(Class<T> clazz, LocalDate date, Student student, MathProblemType mathProblemType, boolean isWrong, String problemNum, HashMap<String, DifficultyLevel> wrongProblemRecordMap, JpaRepository repository) {
        T statistics = null;

        if (clazz == SubconceptStatisticsForWeek.class)
            statistics = (T) subconceptStatisticsForWeekRepository
                    .findByStartOfWeekAndStudentAndType(date, student, mathProblemType);
        else
            statistics = (T) subconceptStatisticsForMonthRepository
                    .findByTargetMonthAndStudentAndType(date, student, mathProblemType);

        //기존 통계가 없다면 새 통계를 생성합니다.
        if (statistics == null) {
            try {
                statistics = clazz.getConstructor(LocalDate.class, Student.class, MathProblemType.class)
                        .newInstance(date, student, mathProblemType);
            } catch (Exception e) {
                throw new RuntimeException("Failed to create statistics instance", e);
            }
        }
        updateStatistics(statistics, isWrong, problemNum, wrongProblemRecordMap);
        repository.save(statistics);
    }

    private void updateStatistics(Statistics statistics, boolean isWrong, String probNum, HashMap<String, DifficultyLevel> wrongProbRecordMap) {
        statistics.increaseSolvedNum();
        if (isWrong) {
            statistics.increaseWrongNum();
            statistics.increaseDifficultyNumByType(wrongProbRecordMap.get(probNum));
        }
    }
}

