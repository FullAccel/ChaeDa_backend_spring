package Chaeda_spring.domain.review_note.dto;

import Chaeda_spring.domain.image.entity.ImageFileExtension;
import Chaeda_spring.global.constant.Chapter;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record ReviewNoteProblemInfo(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        @Schema(description = "문제를 틀린 날짜", example = "2024-05-22")
        LocalDate incorrectDate,

        @Schema(description = "이미지 파일의 고유 키 값", example = "10a99bab-4940-48af-92e7-867a56d6ec79")
        String imageKey,

        @Schema(description = "이미지 파일의 확장자", defaultValue = "PNG")
        ImageFileExtension imageFileExtension,

        @Schema(description = "문제의 정답", example = "42")
        String answer,

        @Schema(description = "교재 ID", example = "1")
        Long textbookId,

        @Schema(description = "문제 번호", example = "101")
        String problemNum,

        @Schema(description = "문제가 속한 챕터")
        Chapter chapter
) {
}
