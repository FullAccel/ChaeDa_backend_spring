package Chaeda_spring.domain.Problem.math;

import Chaeda_spring.global.constant.Chapter;
import Chaeda_spring.global.constant.SubConcept;
import Chaeda_spring.global.constant.Subject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class MathProblemType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Subject subject;
    @Enumerated(EnumType.STRING)
    private Chapter chapter;
    @Enumerated(EnumType.STRING)
    private SubConcept subConcept;

    @OneToMany(mappedBy = "mathProblemType", fetch = FetchType.LAZY)
    private List<ProblemTypeMapping> problemTypeMappings = new ArrayList<>();

}
