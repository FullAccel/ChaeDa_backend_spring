package Chaeda_spring.domain.submission.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    Submission findByHwAnnouncementIdAndStudentId(Long hwAnnouncementId, Long studentId);
}
