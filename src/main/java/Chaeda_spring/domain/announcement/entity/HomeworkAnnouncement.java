package Chaeda_spring.domain.announcement.entity;

import Chaeda_spring.domain.class_group.entity.ClassGroup;
import Chaeda_spring.domain.member.entity.Teacher;
import Chaeda_spring.domain.textbook.entity.Textbook;
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
public class HomeworkAnnouncement extends Announcement {

    @Column(nullable = false)
    private int startPage;

    private int endPage;

    @Column(nullable = false)

    private LocalDateTime deadLineDateTime;

    @Column(nullable = false)
    private LocalDate deadLineDate;

    private int submissionNum = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    private Textbook textbook;

    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}