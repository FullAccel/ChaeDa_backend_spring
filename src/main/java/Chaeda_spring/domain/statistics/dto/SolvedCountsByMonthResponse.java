package Chaeda_spring.domain.statistics.dto;

import Chaeda_spring.domain.statistics.entity.problem_type_statistics.SubconceptStatisticsForMonth;
import Chaeda_spring.global.constant.SubConcept;
import io.swagger.v3.oas.annotations.media.Schema;

public record SolvedCountsByMonthResponse(
        @Schema(description = "개념 ID", example = "1")
        Long typeId,
        @Schema(description = "세부 개념", example = "Operations_of_polynomials")
        SubConcept subConcept,
        @Schema(description = "푼 문항 수", example = "5")
        Long solvedCount,
        @Schema(description = "틀린 문항 수", example = "2")
        Long wrongCount
) {
    public static SolvedCountsByMonthResponse of(SubconceptStatisticsForMonth statistics) {
        return new SolvedCountsByMonthResponse(
                statistics.getType().getId(),
                statistics.getType().getSubConcept(),
                statistics.getSolvedNum(),
                statistics.getWrongNum()
        );
    }
}
