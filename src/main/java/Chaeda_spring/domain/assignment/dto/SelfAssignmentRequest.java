package Chaeda_spring.domain.assignment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

import static Chaeda_spring.global.constant.ValidationConstant.TITLE_LENGTH_MAX;

public record SelfAssignmentRequest(
        @Schema(description = "과제 타이틀", example = "블랙라벨 오늘 분량 끝내기", maxLength = TITLE_LENGTH_MAX)
        String title,
        @Schema(description = "과제할 교재 범위의 시작", example = "50")
        int startPage,
        @Schema(description = "과제할 교재 범위의 끝", example = "56")
        int endPage,
        @Schema(description = "과제 수행할 날짜", example = "2024-04-30", type = "string")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        LocalDate targetDate,
        @Schema(description = "과제를 진행할 교재", example = "1")
        Long textbookId
) {
}
