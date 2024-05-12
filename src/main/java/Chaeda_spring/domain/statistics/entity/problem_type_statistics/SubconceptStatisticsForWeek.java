package Chaeda_spring.domain.statistics.entity.problem_type_statistics;

import Chaeda_spring.domain.Problem.math.MathProblemType;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.global.constant.DifficultyLevel;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
public class SubconceptStatisticsForWeek implements Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private MathProblemType type;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Student student;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate startOfWeek;

    @Column(nullable = false)
    private Long solvedNum;

    @Column(nullable = false)
    private Long wrongNum;

    @Column(nullable = false)
    private Long easyNum;

    @Column(nullable = false)
    private Long middleNum;

    @Column(nullable = false)
    private Long hardNum;

    @Builder
    public SubconceptStatisticsForWeek(MathProblemType type, Student student, LocalDate startOfWeek, Long solvedNum, Long wrongNum, Long easyNum, Long middleNum, Long hardNum) {
        this.type = type;
        this.student = student;
        this.startOfWeek = startOfWeek;
        this.solvedNum = solvedNum;
        this.wrongNum = wrongNum;
        this.easyNum = easyNum;
        this.middleNum = middleNum;
        this.hardNum = hardNum;
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
}
