package Chaeda_spring.deprecated.class_group.entity;

import Chaeda_spring.deprecated.announcement.entity.HwAnnouncement;
import Chaeda_spring.deprecated.course.entity.Course;
import Chaeda_spring.domain.BaseTimeEntity;
import Chaeda_spring.domain.File.entity.Image;
import Chaeda_spring.domain.member.entity.Teacher;
import Chaeda_spring.global.constant.Grade;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Deprecated
@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class ClassGroup extends BaseTimeEntity {


    @OneToOne
    @JoinColumn(name = "image_id")
    Image image;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    //이거 나중에 enum으로 해야할 듯
    private String lessonDays;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Grade grade;
    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;

    @OneToMany(mappedBy = "classGroup")
    private List<HwAnnouncement> homeworkNotificationList = new ArrayList<>();

    @OneToMany(mappedBy = "classGroup")
    private List<Course> courseList = new ArrayList<>();

    @Builder
    public ClassGroup(String name, Grade grade, Image image, Teacher teacher, String lessonDays) {
        this.name = name;
        this.grade = grade;
        this.image = image;
        this.teacher = teacher;
        this.lessonDays = lessonDays;
    }
}
