package Chaeda_spring.domain.statistics.dto;

import Chaeda_spring.domain.statistics.entity.problem_type_statistics.SubconceptStatisticsForWeek;
import Chaeda_spring.global.constant.SubConcept;
import io.swagger.v3.oas.annotations.media.Schema;

public record WrongCountWithSubconceptResponse(
        @Schema(description = "개념 ID", example = "1")
        Long typeId,
        @Schema(description = "세부 개념", example = "Operations_of_polynomials")
        SubConcept subConcept,
        @Schema(description = "푼 문항 수", example = "5")
        Long problemCount,
        @Schema(description = "틀린 문항 수", example = "2")
        Long wrongCount
) {
    public static WrongCountWithSubconceptResponse of(SubconceptStatisticsForWeek statistics) {
        return new WrongCountWithSubconceptResponse(
                statistics.getType().getId(),
                statistics.getType().getSubConcept(),
                statistics.getSolvedNum(),
                statistics.getWrongNum()
        );
    }
}
