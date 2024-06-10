package Chaeda_spring.domain.submission.assignment.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface WrongProblemRecordRepository extends JpaRepository<WrongProblemRecord, Long> {

    @Query("SELECT s FROM WrongProblemRecord s WHERE s.incorrectDate BETWEEN :startDate AND :endDate AND s.student = :student")
    List<WrongProblemRecord> findAllByWrongDateBetween(LocalDate startDate, LocalDate endDate);
}
