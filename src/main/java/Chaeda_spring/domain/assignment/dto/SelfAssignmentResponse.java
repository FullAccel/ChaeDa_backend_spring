package Chaeda_spring.domain.assignment.dto;

import Chaeda_spring.domain.assignment.entity.SelfAssignment;
import Chaeda_spring.domain.textbook.dto.TextbookResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;

import static Chaeda_spring.global.constant.ValidationConstant.TITLE_LENGTH_MAX;

@Builder
public record SelfAssignmentResponse(
        @Schema(description = "과제 id", example = "1")
        Long id,
        @Schema(description = "과제 타이틀", example = "블랙라벨 오늘 분량 끝내기", maxLength = TITLE_LENGTH_MAX)
        String title,
        @Schema(description = "과제할 교재 범위의 시작", example = "50")
        int startPage,
        @Schema(description = "과제할 교재 범위의 끝", example = "56")
        int endPage,
        @Schema(description = "과제 수행할 날짜", example = "2024-04-30", type = "string")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        LocalDate targetDate,
        @Schema(description = "과제를 진행할 교재 정보")
        TextbookResponse textbook,
        @Schema(description = "과제 결과 제출 여부", example = "true")
        boolean isCompleted
) {

    public static SelfAssignmentResponse of(SelfAssignment selfAssignment) {
        return SelfAssignmentResponse.builder()
                .id(selfAssignment.getId())
                .title(selfAssignment.getTitle())
                .startPage(selfAssignment.getStartPage())
                .endPage(selfAssignment.getEndPage())
                .targetDate(selfAssignment.getTargetDate())
                .textbook(TextbookResponse.of(selfAssignment.getTextbook()))
                .isCompleted(selfAssignment.isCompleted())
                .build();
    }
}
