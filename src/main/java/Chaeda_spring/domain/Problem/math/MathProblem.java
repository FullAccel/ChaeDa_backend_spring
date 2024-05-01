package Chaeda_spring.domain.Problem.math;

import Chaeda_spring.domain.textbook.entity.Textbook;
import Chaeda_spring.global.constant.DifficultyLevel;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MathProblem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String problemNumber;
    private int pageNumber;

    private int solvedStudentsCount;
    private int incorrectStudentsCount;
    private int lowDifficultyPerceivedCount;
    private int mediumDifficultyPerceivedCount;
    private int highDifficultyPerceivedCount;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Textbook textbook;

    @Builder

    public MathProblem(String problemNumber, int pageNumber, Textbook textbook) {
        this.problemNumber = problemNumber;
        this.pageNumber = pageNumber;
        this.textbook = textbook;
    }

    public void increaseSolvedStudentsCount() {
        this.solvedStudentsCount++;
    }

    public void increaseIncorrectStudentsCount() {
        this.incorrectStudentsCount++;
    }

    public void increaseCountByDifficulty(DifficultyLevel difficultyLevel) {
        switch (difficultyLevel) {
            case HIGH:
                this.highDifficultyPerceivedCount++;
                break;
            case MEDIUM:
                this.mediumDifficultyPerceivedCount++;
                break;
            case LOW:
                this.lowDifficultyPerceivedCount++;
                break;
        }
    }
}
