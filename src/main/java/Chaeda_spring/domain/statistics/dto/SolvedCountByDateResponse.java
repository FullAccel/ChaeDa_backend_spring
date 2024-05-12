package Chaeda_spring.domain.statistics.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.Map;

public record SolvedCountByDateResponse(
        @Schema(description = "일별 푼 문항 수를 나타내는 맵. 맵의 키는 날짜고, 값은 해당 일의 푼 문항 수입니다.",
                implementation = Map.class,
                example = "{2022-01-01: 10, 2022-01-02: 15}")
        Map<LocalDate, Integer> solvedNumMap
) {

}
