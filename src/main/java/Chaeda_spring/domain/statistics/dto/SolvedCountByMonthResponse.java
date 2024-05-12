package Chaeda_spring.domain.statistics.dto;

import java.util.Map;

public record SolvedCountByMonthResponse(Map<String, Integer> solvedNumMap) {
}
