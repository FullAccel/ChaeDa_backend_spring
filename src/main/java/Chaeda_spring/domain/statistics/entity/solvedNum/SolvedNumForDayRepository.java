package Chaeda_spring.domain.statistics.entity.solvedNum;

import Chaeda_spring.domain.member.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SolvedNumForDayRepository extends JpaRepository<SolvedNumForDay, Long> {
    SolvedNumForDay findByTodayDateAndStudent(LocalDate todayDate, Student student);

    @Query("SELECT s FROM SolvedNumForDay s WHERE s.todayDate >= :todayDate AND s.student = :student")
    List<SolvedNumForDay> findForDaysByTodayDateAndStudent(LocalDate todayDate, Student student);
}
