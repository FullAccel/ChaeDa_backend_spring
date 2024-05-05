package Chaeda_spring.domain.statistics.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface SolvedNumForMonthRepository extends JpaRepository<SolvedNumForMonth, Long> {
    SolvedNumForMonth findByMonthDate(LocalDate monthDate);
}
