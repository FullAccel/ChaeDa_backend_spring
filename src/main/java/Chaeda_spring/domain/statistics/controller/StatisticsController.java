package Chaeda_spring.domain.statistics.controller;

import Chaeda_spring.domain.member.entity.Member;
import Chaeda_spring.domain.statistics.dto.SolvedCountsByMonthResponse;
import Chaeda_spring.domain.statistics.dto.StatisticsBySubconceptResponse;
import Chaeda_spring.domain.statistics.dto.WrongCountWithSubconceptResponse;
import Chaeda_spring.domain.statistics.service.StatisticsService;
import Chaeda_spring.global.annotation.AuthUser;
import Chaeda_spring.global.constant.Chapter;
import Chaeda_spring.global.constant.SubConcept;
import Chaeda_spring.global.constant.Subject;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/solvedCount/7days")
    @Operation(summary = "date로부터 이전 7일동안 일별 푼 문항 수")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Map -> key : yyyy-mm-dd, value: 풀이 횟수")})
    public ResponseEntity<Map<LocalDate, Integer>> getSolvedCountByDate(
            @AuthUser Member member,
            @Schema(description = "날짜 형식은 'yyyy-mm-dd'로 해주세요", example = "2024-04-30")
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
            @RequestParam("date") LocalDate date) {
        return ResponseEntity.ok(statisticsService.getSolvedCountByDate(member, date));
    }

    @GetMapping("/solvedCount/8weeks")
    @Operation(summary = "이번주부터 이전 8주동안 주별 푼 문항 수")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Map -> key : yyyy-mm-dd(주의 시작 월요일), value: 풀이 횟수")})
    public ResponseEntity<Map<LocalDate, Integer>> getSolvedCountByWeek(
            @AuthUser Member member
    ) {
        return ResponseEntity.ok(statisticsService.getSolvedCountByWeek(member));
    }

    @GetMapping("/solvedCount/6months")
    @Operation(summary = "이번달부터 이전 6달 동안 월별 푼 문항 수")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Map -> key : yyyy-mm, value: 풀이 횟수")})
    public ResponseEntity<Map<String, Integer>> getSolvedCountByMonth(
            @AuthUser Member member
    ) {
        return ResponseEntity.ok(statisticsService.getSolvedCountByMonth(member));
    }

    @GetMapping("/wrongCount/weeklyTopSubconcepts")
    @Operation(summary = "해당 주에 대한 상위 10개 오답 개념 조회")
    public ResponseEntity<List<WrongCountWithSubconceptResponse>> getTop10WrongCountWithConceptForWeek(
            @AuthUser Member member,
            @Schema(description = "날짜 형식은 'yyyy-mm-dd'로 해주세요", example = "2024-04-30")
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
            @RequestParam("date") LocalDate date
    ) {
        return ResponseEntity.ok(statisticsService.getTop10WrongCountWithConceptForWeek(date, member));
    }

    @GetMapping("/wrongCount/monthlyTopSubconcepts")
    @Operation(summary = "해당 달에 대한 상위 10개 오답 개념 조회")
    public ResponseEntity<List<SolvedCountsByMonthResponse>> getSolvedCountByMonth(
            @AuthUser Member member,
            @Schema(description = "날짜 형식은 'yyyy-mm'로 해주세요", example = "2024-04")
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM", timezone = "Asia/Seoul")
            @RequestParam("date") LocalDate targetMonth) {
        return ResponseEntity.ok(statisticsService.getTop10WrongCountWithConceptForMonth(member, targetMonth));
    }

    @GetMapping("/chapter/list")
    @Operation(summary = "과목명으로 단원 리스트 조회")
    public ResponseEntity<List<Chapter>> getChapterListBySubject(
            @RequestParam("subject") Subject subject
    ) {
        return ResponseEntity.ok(statisticsService.getChapterListBySubject(subject));
    }

    @GetMapping("/subconcept/list")
    @Operation(summary = "단원명으로 세부개념 리스트 조회")
    public ResponseEntity<List<SubConcept>> getSubconceptListByChapter(
            @RequestParam("chapter") Chapter chapter
    ) {
        return ResponseEntity.ok(statisticsService.getSubconceptListByChapter(chapter));
    }

    @GetMapping("/statistics/weekly/{subConcept}")
    @Operation(summary = "주간 세부개념 통계 조회")
    public ResponseEntity<StatisticsBySubconceptResponse> getWeeklyStatisticsBySubconcept(
            @AuthUser Member member,
            @PathVariable SubConcept subConcept) {
        return ResponseEntity.ok(statisticsService.getWeeklyStatisticsBySubConcept(member, subConcept));
    }

    @GetMapping("/statistics/monthly/{subConcept}")
    @Operation(summary = "월간 세부개념 통계 조회")
    public ResponseEntity<StatisticsBySubconceptResponse> getMonthlyStatisticsBySubconcept(
            @AuthUser Member member,
            @PathVariable SubConcept subConcept) {
        return ResponseEntity.ok(statisticsService.getMonthlyStatisticsBySubConcept(member, subConcept));
    }

    @GetMapping("/statistics/accumulated/{subConcept}")
    @Operation(summary = "누적 세부개념 통계 조회")
    public ResponseEntity<StatisticsBySubconceptResponse> getAccumulatedWeekStatisticsBySubconcept(
            @AuthUser Member member,
            @PathVariable SubConcept subConcept) {
        return ResponseEntity.ok(statisticsService.getAccumulatedStatisticsBySubConcept(member, subConcept));
    }

    @GetMapping("/statistics/accumulated/{chapter}")
    @Operation(summary = "특정 단원에 속하는 모든 세부개념의 누적 통계 리스트 조회", description = "결과에 없는 세부개념은 풀이 횟수가 존재하지 없어 통계에 존재하지 않는 세부개념입니다.")
    public ResponseEntity<List<StatisticsBySubconceptResponse>> getAccumulatedWeekStatisticsBySubconcept(
            @AuthUser Member member,
            @PathVariable Chapter chapter) {
        return ResponseEntity.ok(statisticsService.getAccumulatedStatisticsBySubConceptList(member, chapter));
    }
}
