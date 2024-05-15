package Chaeda_spring.domain.statistics.dto;

import Chaeda_spring.domain.statistics.entity.problem_type_statistics.Statistics;
import Chaeda_spring.global.constant.SubConcept;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record StatisticsBySubconceptResponse(
        @Schema(description = "세부 개념", example = "Polynomial Operations")
        SubConcept subConcept,
        @Schema(description = "푼 문항 수", example = "50")
        int problemCount,
        @Schema(description = "틀린 문항 수", example = "20")
        int wrongCount,
        @Schema(description = "하 난이도로 느낀 틀린문제 수", example = "10")
        int easyNum,
        @Schema(description = "중 난이도로 느낀 틀린문제 수", example = "5")
        int middleNum,
        @Schema(description = "상 난이도로 느낀 틀린문제 수", example = "5")
        int hardNum
) {
    public static StatisticsBySubconceptResponse from(SubConcept subConcept, Statistics statistics) {
        if (statistics == null) {
            return StatisticsBySubconceptResponse.builder()
                    .subConcept(subConcept) // 세부 개념을 알 수 없으므로 null 혹은 적절한 기본 값을 설정
                    .problemCount(0)
                    .wrongCount(0)
                    .easyNum(0)
                    .middleNum(0)
                    .hardNum(0)
                    .build();
        } else {
            return StatisticsBySubconceptResponse.builder()
                    .subConcept(subConcept)
                    .problemCount(statistics.getSolvedNum())
                    .wrongCount(statistics.getWrongNum())
                    .easyNum(statistics.getEasyNum())
                    .middleNum(statistics.getMiddleNum())
                    .hardNum(statistics.getHardNum())
                    .build();
        }
    }
}
