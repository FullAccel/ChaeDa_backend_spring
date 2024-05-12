package Chaeda_spring.domain.statistics.entity.solvedNum;

import Chaeda_spring.domain.member.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface SolvedNumForDayRepository extends JpaRepository<SolvedNumForDay, Long> {
    SolvedNumForDay findByTodayDateAndStudent(LocalDate todayDate, Student student);
}
