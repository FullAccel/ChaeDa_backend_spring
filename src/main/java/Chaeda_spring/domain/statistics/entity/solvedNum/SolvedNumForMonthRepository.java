package Chaeda_spring.domain.statistics.entity.solvedNum;

import Chaeda_spring.domain.member.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SolvedNumForMonthRepository extends JpaRepository<SolvedNumForMonth, Long> {

    SolvedNumForMonth findByMonthDateAndStudent(LocalDate monthDate, Student student);

    @Query("SELECT s FROM SolvedNumForMonth s WHERE s.monthDate >= :monthDate")
    List<SolvedNumForMonth> findForMonthsByMonthDateAndStudent(LocalDate monthDate, Student student);
}
