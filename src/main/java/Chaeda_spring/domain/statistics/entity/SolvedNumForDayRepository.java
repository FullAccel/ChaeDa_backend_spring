package Chaeda_spring.domain.statistics.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface SolvedNumForDayRepository extends JpaRepository<SolvedNumForDay, Long> {
    SolvedNumForDay findByTodayDate(LocalDate todayDate);
}
