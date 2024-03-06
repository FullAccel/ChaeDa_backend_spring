package Chaeda_spring.domain.course.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    void deleteByStudentIdAndClassGroupId(Long studentId, Long classGroupId);
}
