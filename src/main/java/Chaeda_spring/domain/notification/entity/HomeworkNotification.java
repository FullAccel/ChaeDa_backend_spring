package Chaeda_spring.domain.notification.entity;

import Chaeda_spring.domain.class_group.entity.ClassGroup;
import Chaeda_spring.domain.member.entity.Teacher;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Builder;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private ClassGroup classGroup;

    public HomeworkNotification(Long id, String content, int startPage, int endPage, LocalDateTime deadLine, ClassGroup classGroup) {
        super(id, content);
        this.startPage = startPage;
        this.endPage = endPage;
        this.deadLine = deadLine;
        this.classGroup = classGroup;
    }
}
