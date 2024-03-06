package Chaeda_spring.domain.member.dto;

import Chaeda_spring.domain.member.entity.Student;
import lombok.Builder;

@Builder
public record StudentResponse(
        Long id,
        String name,
        String email,
        String gender,
        String phoneNumber,
        String address,
        String profileUrl,
        String schoolName,
        String parentPhoneNum,
        String homePhoneNum,
        String subject,
        String notes
) implements MemberResponse {
    public static StudentResponse from(Student student, String presignedUrl) {
        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .gender(student.getGender())
                .phoneNumber(student.getPhoneNumber())
                .address(student.getAddress())
                .profileUrl(presignedUrl)
                .schoolName(student.getSchoolName())
                .parentPhoneNum(student.getParentPhoneNum())
                .homePhoneNum(student.getHomePhoneNum())
                .subject(student.getSubject())
                .notes(student.getNotes())
                .build();

    }
}
