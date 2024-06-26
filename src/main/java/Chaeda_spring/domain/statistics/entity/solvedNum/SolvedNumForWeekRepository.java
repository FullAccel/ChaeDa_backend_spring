package Chaeda_spring.domain.statistics.entity.solvedNum;

import Chaeda_spring.domain.member.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SolvedNumForWeekRepository extends JpaRepository<SolvedNumForWeek, Long> {
    SolvedNumForWeek findByStartOfWeekAndStudent(LocalDate startOfWeek, Student student);

    @Query("SELECT s FROM SolvedNumForWeek s WHERE s.startOfWeek BETWEEN :startDate AND :endDate AND s.student = :student")
    List<SolvedNumForWeek> findForWeeksBetweenStartDateAndEndDateAndStudent(LocalDate startDate, LocalDate endDate, Student student);
}
