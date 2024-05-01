package Chaeda_spring.domain.assignment.entity;

import Chaeda_spring.domain.member.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SelfAssignmentRepository extends JpaRepository<SelfAssignment, Long> {
    List<SelfAssignment> findAllByTargetDateAndStudent(LocalDate targetDate, Student student);
}
