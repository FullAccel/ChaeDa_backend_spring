package Chaeda_spring.domain.submission.assignment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
public record ProblemNumScopeResponse(
        @Schema(description = "페이지 번호", example = "12")
        int pageNum,
        @Schema(description = "문제 번호 리스트", example = "{'1', '2', '3'}")
        List<String> problemNumbers
) {

    public static ProblemNumScopeResponse of(int pageNum, List<String> problemNumbers) {
        return ProblemNumScopeResponse.builder()
                .pageNum(pageNum)
                .problemNumbers(problemNumbers)
                .build();
    }
}
