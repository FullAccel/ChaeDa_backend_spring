package Chaeda_spring.domain.class_group.entity;

import Chaeda_spring.domain.BaseTimeEntity;
import Chaeda_spring.domain.member.entity.Teacher;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ClassGroup extends BaseTimeEntity {

    @Id
    @Column(name = "CLASS_GROUP_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String grade;

    @Column(nullable = false)
    //이거 나중에 enum으로 해야할 듯
    private String lessonDays;

    private String profileUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;


    @Builder
    public ClassGroup(String name, String grade, String lessonDays, String profileUrl) {
        this.name = name;
        this.grade = grade;
        this.lessonDays = lessonDays;
        this.profileUrl = profileUrl;
    }
}
