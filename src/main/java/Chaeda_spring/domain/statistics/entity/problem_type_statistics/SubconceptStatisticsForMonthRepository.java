package Chaeda_spring.domain.statistics.entity.problem_type_statistics;

import Chaeda_spring.domain.Problem.math.MathProblemType;
import Chaeda_spring.domain.member.entity.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SubconceptStatisticsForMonthRepository extends JpaRepository<SubconceptStatisticsForMonth, Long> {

    SubconceptStatisticsForMonth findByTargetMonthAndStudentAndType(LocalDate targetMonth, Student student, MathProblemType type);

    @Query(value = """
            SELECT
                s
            FROM 
                SubconceptStatisticsForMonth s
            WHERE 
                s.targetMonth = :targetMonth
            ORDER BY 
                s.wrongNum DESC
            """)
    List<SubconceptStatisticsForMonth> findTop10WrongCountByMonthAndStudent(LocalDate targetMonth, Student student, Pageable pageable);
}
