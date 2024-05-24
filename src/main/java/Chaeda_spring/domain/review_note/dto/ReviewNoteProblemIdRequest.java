package Chaeda_spring.domain.review_note.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record ReviewNoteProblemIdRequest(
        @Schema(example = "{1,2,3,4,5,6}")
        List<Long> reviewNoteProblemIds,
        @Schema(example = "오답노트 제목")
        String title,
        @Schema(example = "오답노트 간단 설명")
        String description
) {
}
