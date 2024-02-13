package Chaeda_spring.domain.submission.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    Submission findByHomeworkNotificationIdAndStudentId(Long homeworkNotificationId, Long studentId);
}
