package Chaeda_spring.domain.class_group.dto;

import Chaeda_spring.domain.class_group.entity.ClassGroup;
import Chaeda_spring.domain.class_group.entity.Grade;
import Chaeda_spring.domain.member.dto.StudentSummaryResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
public record ClassGroupResponse(
        @Schema(description = "클래스 Id", example = "1")
        Long id,
        @Schema(description = "클래스 명", example = "고3 월수금 1시")
        String name,
        @Schema(description = "클래스 학년", example = "HIGH_3")
        Grade grade,
        @Schema(description = "클래스 수업 날", example = "MWF")
        String lessonDays,
        @Schema(description = "클래스 프로필 url")
        String presignedUrl,
        @Schema(description = "클래스 소속 학생들 요약 정보", example = "[{'id': 1, 'name': '김철수', 'grade': 'HIGH_3'}, {'id': 2, 'name': '이영희', 'grade': 'HIGH_3'}]")
        List<StudentSummaryResponse> studentSummaryResponseList,
        Long imageId
) {
    public static ClassGroupResponse from(ClassGroup classGroup, String presignedUrl, List<StudentSummaryResponse> studentSummaryResponseList) {
        return ClassGroupResponse.builder()
                .id(classGroup.getId())
                .name(classGroup.getName())
                .grade(classGroup.getGrade())
                .lessonDays(classGroup.getLessonDays())
                .presignedUrl(presignedUrl)
                .studentSummaryResponseList(studentSummaryResponseList)
                .imageId(classGroup.getImage().getId())
                .build();
    }
}
