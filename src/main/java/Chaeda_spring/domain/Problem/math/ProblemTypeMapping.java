package Chaeda_spring.domain.Problem.math;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ProblemTypeMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "math_problem_id")
    private MathProblem mathProblem;

    @ManyToOne
    @JoinColumn(name = "math_problem_type_id")
    private MathProblemType mathProblemType;
}
