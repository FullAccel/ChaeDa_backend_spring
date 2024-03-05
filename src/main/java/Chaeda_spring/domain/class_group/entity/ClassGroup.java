package Chaeda_spring.domain.class_group.entity;

import Chaeda_spring.domain.BaseTimeEntity;
import Chaeda_spring.domain.announcement.entity.HwAnnouncement;
import Chaeda_spring.domain.course.entity.Course;
import Chaeda_spring.domain.image.entity.Image;
import Chaeda_spring.domain.member.entity.Teacher;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class ClassGroup extends BaseTimeEntity {


    @OneToOne
    @JoinColumn(name = "image_id")
    Image image;
    @Id
    @Column(name = "CLASS_GROUP_ID")
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
