package Chaeda_spring.domain.statistics.entity.problem_type_statistics;

import Chaeda_spring.domain.Problem.math.MathProblemType;
import Chaeda_spring.domain.member.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface SubconceptStatisticsForMonthRepository extends JpaRepository<SubconceptStatisticsForMonth, Long> {

    SubconceptStatisticsForMonth findByTargetMonthAndStudentAndType(LocalDate targetMonth, Student student, MathProblemType type);

}
