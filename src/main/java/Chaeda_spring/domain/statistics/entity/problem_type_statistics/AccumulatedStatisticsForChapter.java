package Chaeda_spring.domain.statistics.entity.problem_type_statistics;

import Chaeda_spring.domain.Problem.math.MathProblemType;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.global.constant.Chapter;
import Chaeda_spring.global.constant.DifficultyLevel;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class AccumulatedStatisticsForChapter implements Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Student student;
    @Column(nullable = false)
    private int solvedNum;
    @Column(nullable = false)
    private int wrongNum;
    @Column(nullable = false)
    private int easyNum;
    @Column(nullable = false)
    private int middleNum;
    @Column(nullable = false)
    private int hardNum;

    @Enumerated(EnumType.STRING)
    private Chapter chapter;

    @Builder
    public AccumulatedStatisticsForChapter(Student student, int solvedNum, int wrongNum, int easyNum, int middleNum, int hardNum, Chapter chapter) {
        this.student = student;
        this.solvedNum = solvedNum;
        this.wrongNum = wrongNum;
        this.easyNum = easyNum;
        this.middleNum = middleNum;
        this.hardNum = hardNum;
        this.chapter = chapter;
    }

    @Override
    public void increaseSolvedNum() {
        solvedNum++;
    }

    @Override
    public void increaseWrongNum() {
        wrongNum++;
    }

    @Override
    public void increaseDifficultyNumByType(DifficultyLevel difficultyLevel) {
        switch (difficultyLevel) {
            case LOW:
                easyNum++;
                break;
            case MEDIUM:
                middleNum++;
                break;
            case HIGH:
                hardNum++;
                break;
        }
    }

    @Override
    public MathProblemType getType() {
        return null;
    }

    @Override
    public int getSolvedNum() {
        return solvedNum;
    }

    @Override
    public int getWrongNum() {
        return wrongNum;
    }

    @Override
    public int getEasyNum() {
        return easyNum;
    }

    @Override
    public int getMiddleNum() {
        return middleNum;
    }

    @Override
    public int getHardNum() {
        return hardNum;
    }
}
