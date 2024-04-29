package Chaeda_spring.domain.member.dto;

import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.global.constant.Grade;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record StudentResponse(
        Long id,
        String name,
        String email,
        String gender,
        String phoneNumber,
        String address,
        @Schema(description = "프로필이미지 url", nullable = true)
        String profileUrl,
        String schoolName,
        @Schema(description = "부모님 전화번호", nullable = true)
        String parentPhoneNum,
        @Schema(description = "집 전화번호", nullable = true)
        String homePhoneNum,
        Grade grade,
        @Schema(description = "특이사항", nullable = true)
        String notes,
        @Schema(description = "프로필이미지 아이디", nullable = true)
        Long imageId
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
                .grade(student.getGrade())
                .notes(student.getNotes())
                .imageId(presignedUrl != null ? student.getImage().getId() : null)
                .build();

    }
}
