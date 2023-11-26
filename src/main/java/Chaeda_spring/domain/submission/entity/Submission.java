package Chaeda_spring.domain.submission.entity;

import Chaeda_spring.domain.BaseTimeEntity;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.notification.entity.HomeworkNotification;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
public class Submission extends BaseTimeEntity {

    @Id
    @Column(name = "SUBMISSION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean status = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hw_notification_id")
    private HomeworkNotification homeworkNotification;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    public void setHomeworkNotification(HomeworkNotification homeworkNotification) {
        this.homeworkNotification = homeworkNotification;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
