package Chaeda_spring.domain.class_group.dto;

import Chaeda_spring.domain.class_group.entity.ClassGroup;
import Chaeda_spring.domain.class_group.entity.Grade;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record ClassGroupSummaryResponse(
        @Schema(description = "클래스 Id", example = "1")
        Long id,
        @Schema(description = "클래스 명", example = "고3 월수금 1시")
        String name,
        @Schema(description = "클래스 학년", example = "HIGH_3")
        Grade grade,
        @Schema(description = "클래스 수업 날", example = "MWF")
        String lessonDays,
        @Schema(description = "클래스 프로필 url")
        String presignedUrl
) {
    public static ClassGroupSummaryResponse from(ClassGroup classGroup, String presignedUrl) {
        return ClassGroupSummaryResponse.builder()
                .id(classGroup.getId())
                .grade(classGroup.getGrade())
                .name(classGroup.getName())
                .lessonDays(classGroup.getLessonDays())
                .presignedUrl(presignedUrl)
                .build();
    }
}
