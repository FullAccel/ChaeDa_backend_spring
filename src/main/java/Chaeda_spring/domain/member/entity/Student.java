package Chaeda_spring.domain.member.entity;

import Chaeda_spring.domain.course.entity.Course;
import Chaeda_spring.domain.submission.entity.Submission;
import Chaeda_spring.global.constant.Grade;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@DiscriminatorValue("Student")
public class Student extends Member {

    @Column(nullable = false)
    private String schoolName;

    private String parentPhoneNum;

    private String homePhoneNum;

    private String notes;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @OneToMany(mappedBy = "student")
    private List<Course> courseList = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<Submission> submissionList = new ArrayList<>();

    public Student(String name, String email, String gender, Role role, String phoneNumber, String address, String profileUrl, String schoolName, String parentPhoneNum, String homePhoneNum, String subject, String notes) {
        super(name, email, gender, phoneNumber, address, role);
        this.schoolName = schoolName;
        this.parentPhoneNum = parentPhoneNum;
        this.homePhoneNum = homePhoneNum;
        this.notes = notes;
    }
}
