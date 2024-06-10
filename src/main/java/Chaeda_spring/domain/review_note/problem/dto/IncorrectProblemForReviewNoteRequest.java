package Chaeda_spring.domain.review_note.problem.dto;

import Chaeda_spring.domain.Problem.math.MathProblem;
import Chaeda_spring.domain.submission.assignment.entity.WrongProblemRecord;
import Chaeda_spring.global.constant.Chapter;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public record IncorrectProblemForReviewNoteRequest(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        @Schema(description = "문제를 틀린 날짜", example = "2024-05-22")
        LocalDate incorrectDate,
        @Schema(description = "교재 이름", example = "쎈 미적분")
        String textbookName,

        @Schema(description = "문제 번호", example = "101")
        String problemNum,

        @Schema(description = "문제가 속한 챕터")
        List<Chapter> chapters
) {
    public static IncorrectProblemForReviewNoteRequest from(WrongProblemRecord record) {
        MathProblem mathProblem = record.getMathProblem();
        List<Chapter> chapters = mathProblem.getProblemTypeMappings().stream()
                .map(type -> type.getMathProblemType().getChapter())
                .collect(Collectors.toList());
        return IncorrectProblemForReviewNoteRequest.builder()
                .incorrectDate(record.getIncorrectDate())
                .textbookName(mathProblem.getTextbook().getName())
                .problemNum(mathProblem.getProblemNumber())
                .chapters(chapters)
                .build();
    }
}
