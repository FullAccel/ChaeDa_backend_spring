package Chaeda_spring.domain.member.entity;

import Chaeda_spring.deprecated.course.entity.Course;
import Chaeda_spring.domain.assignment.entity.SelfAssignment;
import Chaeda_spring.domain.statistics.entity.solvedNum.SolvedNumForDay;
import Chaeda_spring.domain.submission.homework.entity.homework.SubmissionHomework;
import Chaeda_spring.global.constant.Grade;
import Chaeda_spring.global.constant.Role;
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
    private List<SubmissionHomework> submissionHomeworkList = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<SelfAssignment> selfAssignments = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<SolvedNumForDay> solvedNumForDays = new ArrayList<>();

    public Student(String name, String email, String gender, Role role, String phoneNumber, String address, String profileUrl, String schoolName, String parentPhoneNum, String homePhoneNum, String subject, String notes) {
        super(name, email, gender, phoneNumber, address, role);
        this.schoolName = schoolName;
        this.parentPhoneNum = parentPhoneNum;
        this.homePhoneNum = homePhoneNum;
        this.notes = notes;
    }
}
