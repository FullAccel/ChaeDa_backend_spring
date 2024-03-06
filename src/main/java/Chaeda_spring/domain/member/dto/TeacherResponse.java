package Chaeda_spring.domain.member.dto;

import Chaeda_spring.domain.member.entity.Teacher;
import lombok.Builder;


@Builder
public record TeacherResponse(
        Long id,
        String name,
        String email,
        String gender,
        String phoneNumber,
        String address,
        String profileUrl,
        String notes,
        String subject
) implements MemberResponse {
    public static TeacherResponse from(Teacher teacher, String presignedUrl) {
        return TeacherResponse.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .email(teacher.getEmail())
                .gender(teacher.getGender())
                .phoneNumber(teacher.getPhoneNumber())
                .address(teacher.getAddress())
                .profileUrl(presignedUrl)
                .subject(teacher.getSubject())
                .notes(teacher.getNotes())
                .build();

    }
}
