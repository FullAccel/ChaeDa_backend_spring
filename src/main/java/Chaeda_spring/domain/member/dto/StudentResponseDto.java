package Chaeda_spring.domain.member.dto;

import Chaeda_spring.domain.member.entity.Student;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudentResponseDto {

    private Long id;

    private String name;

    private String email;

    private String gender;

    private String phoneNumber;

    private String address;

    private String profileUrl;

    private String schoolName;

    private String parentPhoneNum;

    private String homePhoneNum;

    private String subject;

    private String notes;

    @Builder
    public StudentResponseDto(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.email = student.getEmail();
        this.gender = student.getGender();
        this.phoneNumber = student.getPhoneNumber();
        this.address = student.getAddress();
        this.profileUrl = student.getProfileUrl();
        this.schoolName = student.getSchoolName();
        this.parentPhoneNum = student.getParentPhoneNum();
        this.homePhoneNum = student.getHomePhoneNum();
        this.subject = student.getSubject();
        this.notes = student.getNotes();
    }
}
