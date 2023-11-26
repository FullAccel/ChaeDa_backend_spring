package Chaeda_spring.domain.member.entity;

import Chaeda_spring.domain.course.entity.Course;
import Chaeda_spring.domain.submission.entity.Submission;
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
@DiscriminatorValue("Student")
public class Student extends Member{

    @Column(nullable = false)
    private String schoolName;

    @Column(nullable = false)
    private String parentPhoneNum;

    private String homePhoneNum;

    private String subject;

    private String notes;

    @OneToMany(mappedBy = "student")
    private List<Course> courseList = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<Submission> submissionList = new ArrayList<>();

    public Student(String name, String email, String gender, String phoneNumber, String address, String profileUrl, String schoolName, String parentPhoneNum, String homePhoneNum, String subject, String notes) {
        super(name, email, gender, phoneNumber, address, profileUrl);
        this.schoolName = schoolName;
        this.parentPhoneNum = parentPhoneNum;
        this.homePhoneNum = homePhoneNum;
        this.subject = subject;
        this.notes = notes;
    }
}
