package Chaeda_spring.domain.notification.entity;

import Chaeda_spring.domain.class_group.entity.ClassGroup;
import Chaeda_spring.domain.member.entity.Teacher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
public class HomeworkNotification extends Notification{

    @Column(nullable = false)
    private int startPage;

    private int endPage;

    @Column(nullable = false)
    private LocalDateTime deadLineDateTime;

    @Column(nullable = false)
    private LocalDate deadLineDate;

    private int submissionNum = 0;

    private String textbookImageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private ClassGroup classGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;


    public void setTargetClassGroup(ClassGroup classGroup) {
        this.classGroup = classGroup;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setTextbookImageUrl(String textbookImageUrl) {
        this.textbookImageUrl = textbookImageUrl;
    }
}
