package Chaeda_spring.domain.statistics.entity.problem_type_statistics;

import Chaeda_spring.domain.Problem.math.MathProblemType;
import Chaeda_spring.domain.member.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccumulatedStatisticsForSubconceptRepository extends JpaRepository<AccumulatedStatisticsForSubconcept, Long> {

    AccumulatedStatisticsForSubconcept findByStudentAndType(Student student, MathProblemType type);
}
