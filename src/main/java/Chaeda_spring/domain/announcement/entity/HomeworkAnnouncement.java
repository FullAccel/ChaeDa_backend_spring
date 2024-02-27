package Chaeda_spring.domain.announcement.entity;

import Chaeda_spring.domain.announcement.dto.HwAnnouncementRequest;
import Chaeda_spring.domain.class_group.entity.ClassGroup;
import Chaeda_spring.domain.member.entity.Teacher;
import Chaeda_spring.domain.textbook.entity.Textbook;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
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

    public static HomeworkAnnouncement from(HwAnnouncementRequest request, Textbook textbook, Teacher teacher, ClassGroup classGroup) {
        return HomeworkAnnouncement.builder()
                .title(request.title())
                .content(request.content())
                .startPage(request.startPage())
                .endPage(request.endPage())
                .deadLineDateTime(request.deadLine())
                .deadLineDate(request.deadLine().toLocalDate())
                .teacher(teacher)
                .classGroup(classGroup)
                .textbook(textbook)
                .build();
    }

}
