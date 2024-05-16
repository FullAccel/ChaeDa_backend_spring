package Chaeda_spring.domain.submission.assignment.service;

import Chaeda_spring.domain.Problem.math.MathProblemType;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.statistics.entity.problem_type_statistics.*;
import Chaeda_spring.global.constant.DifficultyLevel;
import Chaeda_spring.global.utils.MemberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AccumulatedStatisticsUpdater {

    private final AccumulatedStatisticsForSubconceptRepository accumulatedStatisticsForSubconceptRepository;

    private final AccumulatedStatisticsForChapterRepository accumulatedStatisticsForChapterRepository;
    private final MemberUtils memberUtils;

    public void updateAccumulatedStatistics(MathProblemType type, boolean isWrong, String problemNum, HashMap<String, DifficultyLevel> wrongProblemRecordMap) {
        Student student = (Student) memberUtils.getCurrentMember();

        updateStatistics(
                AccumulatedStatisticsForSubconcept.class,
                student,
                type,
                isWrong,
                problemNum,
                wrongProblemRecordMap,
                accumulatedStatisticsForSubconceptRepository
        );
        updateStatistics(
                AccumulatedStatisticsForChapter.class,
                student,
                type,
                isWrong,
                problemNum,
                wrongProblemRecordMap,
                accumulatedStatisticsForChapterRepository);
    }

    private <T extends Statistics> void updateStatistics(Class<T> clazz, Student student, MathProblemType mathProblemType, boolean isWrong, String problemNum, HashMap<String, DifficultyLevel> wrongProblemRecordMap, JpaRepository repository) {
        T statistics = null;

        if (clazz == AccumulatedStatisticsForSubconcept.class) {
            statistics = (T) accumulatedStatisticsForSubconceptRepository
                    .findByStudentAndType(student, mathProblemType);
            if (statistics == null) {
                statistics = (T) AccumulatedStatisticsForSubconcept.builder()
                        .student(student)
                        .mathProblemType(mathProblemType)
                        .build();
            }
        } else {
            statistics = (T) accumulatedStatisticsForChapterRepository
                    .findByStudentAndChapter(student, mathProblemType.getChapter());
            if (statistics == null) {
                statistics = (T) AccumulatedStatisticsForChapter.builder()
                        .student(student)
                        .chapter(mathProblemType.getChapter())
                        .build();
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

