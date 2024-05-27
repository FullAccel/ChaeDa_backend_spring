package Chaeda_spring.domain.review_note.entity;

import Chaeda_spring.domain.member.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewNoteProblemRepository extends JpaRepository<ReviewNoteProblem, Long> {

    List<ReviewNoteProblem> findAllByStudent(Student student);

    void deleteByStudentAndId(Student student, Long id);
}
