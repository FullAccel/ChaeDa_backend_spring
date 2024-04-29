package Chaeda_spring.domain.member.dto;

import Chaeda_spring.domain.member.entity.Role;
import Chaeda_spring.domain.member.entity.Teacher;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;


@Builder
public record TeacherResponse(
        Long id,
        String name,
        String email,
        String gender,
        String phoneNumber,
        Role role,
        @Schema(description = "주소", nullable = true)
        String address,
        @Schema(description = "프로필이미지 url", nullable = true)
        String profileUrl,
        @Schema(description = "특이사항", nullable = true)
        String notes,
        @Schema(description = "수업 과목", nullable = true)
        String subject,
        @Schema(description = "프로필이미지 id", nullable = true)
        Long imageId
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
                .role(teacher.getRole())
                .imageId(presignedUrl != null ? teacher.getImage().getId() : null)
                .build();

    }
}
