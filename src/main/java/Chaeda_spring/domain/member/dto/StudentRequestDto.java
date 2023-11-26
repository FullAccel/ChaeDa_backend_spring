package Chaeda_spring.domain.member.dto;

import Chaeda_spring.domain.member.entity.Student;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudentRequestDto extends MemberRequestDto{

    private String schoolName;

    private String parentPhoneNum;

    private String homePhoneNum;

    private String subject;

    private String notes;

    public Student toEntity(){
        return Student.builder()
                .name(this.name)
                .email(this.email)
                .gender(this.gender)
                .phoneNumber(this.phoneNumber)
                .address(this.address)
                .profileUrl(this.profileUrl)
                .schoolName(this.schoolName)
                .parentPhoneNum(this.parentPhoneNum)
                .homePhoneNum(this.homePhoneNum)
                .subject(this.subject)
                .notes(this.notes)
                .build();
    }
}
