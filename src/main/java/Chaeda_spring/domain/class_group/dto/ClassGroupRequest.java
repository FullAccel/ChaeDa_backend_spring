package Chaeda_spring.domain.class_group.dto;

import Chaeda_spring.domain.image.entity.ImageFileExtension;
import Chaeda_spring.domain.image.entity.ImageType;
import Chaeda_spring.global.constant.Grade;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ClassGroupRequest(
        @Schema(description = "이미지 파일의 타입", defaultValue = "ANNOUNCEMENT_THUMBNAIL")
        ImageType imageType,
        @Schema(description = "이미지 파일의 확장자", defaultValue = "PNG")
        ImageFileExtension imageFileExtension,
        @Schema(description = "이미지 파일의 고유 키 값", defaultValue = "10a99bab-4940-48af-92e7-867a56d6ec79")
        String imageKey,
        @NotNull @Schema(description = "클래스 명", defaultValue = "클래스 이름")
        String name,

        @NotNull @Schema(description = "수업 날짜", defaultValue = "고등 월수금반 3시")
        String lessonDays,

        @Schema(description = "학년", defaultValue = "HIGH_1")
        Grade grade,
        @Schema(description = "학생 목록")
        List<Long> studentIdList,
        @Schema(description = "선생님 id", defaultValue = "1")
        Long teacherId
) {
}
