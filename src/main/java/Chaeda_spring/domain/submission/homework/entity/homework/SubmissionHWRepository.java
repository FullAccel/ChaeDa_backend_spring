package Chaeda_spring.domain.submission.homework.entity.homework;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionHWRepository extends JpaRepository<SubmissionHomework, Long> {

    SubmissionHomework findByHwAnnouncementIdAndStudentId(Long hwAnnouncementId, Long studentId);

}
