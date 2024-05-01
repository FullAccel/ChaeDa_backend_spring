package Chaeda_spring.domain.Problem.math;

import Chaeda_spring.domain.textbook.entity.Textbook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MathProblemRepository extends JpaRepository<MathProblem, Long> {


    List<MathProblem> findAllByTextbookAndPageNumber(Textbook textbook, Integer pageNumber);

    MathProblem findMathProblemByTextbookAndPageNumberAndProblemNumber(Textbook textbook, Integer pageNumber, String problemNum);
}
