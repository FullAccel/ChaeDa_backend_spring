package Chaeda_spring.deprecated.announcement.entity;

import Chaeda_spring.deprecated.announcement.dto.HwAnnouncementRequest;
import Chaeda_spring.deprecated.class_group.entity.ClassGroup;
import Chaeda_spring.domain.member.entity.Teacher;
import Chaeda_spring.domain.submission.homework.entity.homework.SubmissionHomework;
import Chaeda_spring.domain.textbook.entity.Textbook;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Deprecated
@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
public class HwAnnouncement extends Announcement {

    @Column(nullable = false)
    private int startPage;

    private int endPage;

    @Column(nullable = false)
    private LocalDateTime deadLineDateTime;

    @Column(nullable = false)
    private LocalDate deadLineDate;

    private int submissionNum = 0;

    @OneToMany(mappedBy = "hwAnnouncement")
    private List<SubmissionHomework> submissionHomeworkList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Textbook textbook;

    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;

    public static HwAnnouncement from(HwAnnouncementRequest request, Textbook textbook, Teacher teacher, ClassGroup classGroup) {
        return HwAnnouncement.builder()
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

    public void plusSubmissionNum() {
        this.submissionNum += 1;
    }
}
