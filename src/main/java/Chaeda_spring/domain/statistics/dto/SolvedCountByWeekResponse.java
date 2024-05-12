package Chaeda_spring.domain.statistics.dto;

import java.time.LocalDate;
import java.util.Map;

public record SolvedCountByWeekResponse(Map<LocalDate, Integer> solvedNumMap) {
}
