package Chaeda_spring.domain.review_note.problem.dto;

import Chaeda_spring.domain.review_note.problem.entity.ReviewNoteProblem;
import Chaeda_spring.global.constant.Chapter;
import Chaeda_spring.global.constant.FileExtension;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ReviewNoteProblemInfo(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        @Schema(description = "문제를 틀린 날짜", example = "2024-05-22")
        LocalDate incorrectDate,

        @Schema(description = "이미지 파일의 고유 키 값", example = "10a99bab-4940-48af-92e7-867a56d6ec79")
        String imageKey,

        @Schema(description = "이미지 파일의 확장자", defaultValue = "PNG")
        FileExtension fileExtension,

        @Schema(description = "문제의 정답", example = "42")
        String answer,

        @Schema(description = "교재 이름", example = "쎈 미적분")
        String textbookName,

        @Schema(description = "문제 번호", example = "101")
        String problemNum,

        @Schema(description = "문제가 속한 챕터")
        Chapter chapter
) {

    public static ReviewNoteProblemInfo from(ReviewNoteProblem entity) {
        return ReviewNoteProblemInfo.builder()
                .incorrectDate(entity.getIncorrectDate())
                .imageKey(entity.getImageKey())
                .fileExtension(entity.getFileExtension())
                .answer(entity.getAnswer())
                .textbookName(entity.getTextbookName())
                .problemNum(entity.getProblemNum())
                .chapter(entity.getChapter())
                .build();
    }

}
