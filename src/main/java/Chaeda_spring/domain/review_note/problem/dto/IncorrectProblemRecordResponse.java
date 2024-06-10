package Chaeda_spring.domain.review_note.problem.dto;

import Chaeda_spring.global.constant.DifficultyLevel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record IncorrectProblemRecordResponse(
        @Schema(description = "틀린 문제 ID", example = "1")
        Long id,
        @Schema(description = "틀린 날짜", example = "2024-04-15")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate incorrectDate,
        @Schema(description = "난이도", example = "HARD")
        DifficultyLevel difficulty,
        @Schema(description = "문제 반호", example = "1")
        String problemNumber
) {
}
