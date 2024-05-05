package Chaeda_spring.domain.statistics.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface SolvedNumForWeekRepository extends JpaRepository<SolvedNumForWeek, Long> {
    SolvedNumForWeek findByStartOfWeek(LocalDate startOfWeek);
}
