package Chaeda_spring.domain.Problem.math;

import Chaeda_spring.global.constant.Chapter;
import Chaeda_spring.global.constant.SubConcept;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MathProblemTypeRepository extends JpaRepository<MathProblemType, Long> {

    MathProblemType findBySubConcept(SubConcept subConcept);

    List<MathProblemType> findAllByChapter(Chapter chapter);
}
