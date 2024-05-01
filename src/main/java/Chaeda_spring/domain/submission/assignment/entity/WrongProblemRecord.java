package Chaeda_spring.domain.submission.assignment.entity;

import Chaeda_spring.domain.Problem.math.MathProblem;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.global.constant.DifficultyLevel;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
public class WrongProblemRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private LocalDate wrongDate;

    @Enumerated(EnumType.STRING)
    private DifficultyLevel difficulty;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private MathProblem mathProblem;

    @Builder
    public WrongProblemRecord(LocalDate wrongDate, DifficultyLevel difficulty, Student student, MathProblem mathProblem) {
        this.wrongDate = wrongDate;
        this.difficulty = difficulty;
        this.student = student;
        this.mathProblem = mathProblem;
    }
}
