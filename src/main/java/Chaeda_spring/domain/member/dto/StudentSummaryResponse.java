package Chaeda_spring.domain.member.dto;

import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.global.constant.Grade;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record StudentSummaryResponse(
        @Schema(description = "학생이름", example = "홍길동")
        String name,
        @Schema(description = "학년", example = "HIGH_1")
        Grade grade,
        @Schema(description = "학교이름", example = "대진고등학교")
        String schoolName,
        @Schema(description = "전화번호", example = "010-1234-5678")
        String phoneNumber
) {
    public static StudentSummaryResponse of(Student student) {
        return StudentSummaryResponse.builder()
                .name(student.getName())
                .grade(student.getGrade())
                .schoolName(student.getSchoolName())
                .phoneNumber(student.getPhoneNumber())
                .build();
    }
}
