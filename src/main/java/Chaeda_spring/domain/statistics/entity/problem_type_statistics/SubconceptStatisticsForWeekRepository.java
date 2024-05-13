package Chaeda_spring.domain.statistics.entity.problem_type_statistics;

import Chaeda_spring.domain.Problem.math.MathProblemType;
import Chaeda_spring.domain.member.entity.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SubconceptStatisticsForWeekRepository extends JpaRepository<SubconceptStatisticsForWeek, Long> {

    SubconceptStatisticsForWeek findByStartOfWeekAndStudentAndType(LocalDate startOfWeek, Student student, MathProblemType type);

    @Query(value = """
            SELECT
                s
            FROM 
                SubconceptStatisticsForWeek s
            WHERE 
                s.startOfWeek = :startOfWeek AND s.student = :student
            ORDER BY 
                s.wrongNum DESC
            """)
    List<SubconceptStatisticsForWeek> findTop10WrongCountByWeekAndStudent(LocalDate startOfWeek, Student student, Pageable pageable);
}
