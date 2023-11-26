package Chaeda_spring.domain.notification.entity;

import Chaeda_spring.domain.class_group.entity.ClassGroup;
import Chaeda_spring.domain.member.entity.Teacher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
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
    private LocalDateTime deadLine;

    private int submissionNum = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    private ClassGroup classGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;

    public HomeworkNotification(Long id, String content, int startPage, int endPage, LocalDateTime deadLine) {
        super(id, content);
        this.startPage = startPage;
        this.endPage = endPage;
        this.deadLine = deadLine;
    }

    public void setTargetClassGroup(ClassGroup classGroup) {
        this.classGroup = classGroup;
    }
}
