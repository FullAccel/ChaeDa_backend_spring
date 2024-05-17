package Chaeda_spring.domain.assignment.controller;

import Chaeda_spring.domain.assignment.dto.SelfAssignmentRequest;
import Chaeda_spring.domain.assignment.dto.SelfAssignmentResponse;
import Chaeda_spring.domain.assignment.service.SelfAssignmentService;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/assignment")
public class SelfAssignmentController {

    private final SelfAssignmentService selfAssignmentService;

    @PostMapping
    @Operation(summary = "개인 과제 생성하기")
    public ResponseEntity<SelfAssignmentResponse> createSelfAssignment(@RequestBody SelfAssignmentRequest dto) {
        return ResponseEntity.ok(selfAssignmentService.createSelfAssignment(dto));
    }

    @PutMapping("/{assignmentId}")
    @Operation(summary = "개인 과제 수정하기")
    public ResponseEntity<SelfAssignmentResponse> updateSelfAssignment(
            @PathVariable Long assignmentId,
            @RequestBody SelfAssignmentRequest dto) {
        return ResponseEntity.ok(selfAssignmentService.updateSelfAssignment(assignmentId, dto));
    }

    @GetMapping("/{assignmentId}")
    @Operation(summary = "개인 과제 id로 조회하기")
    public ResponseEntity<SelfAssignmentResponse> getSelfAssignment(@PathVariable Long assignmentId) {
        return ResponseEntity.ok(selfAssignmentService.getSelfAssignmentById(assignmentId));
    }

    @GetMapping
    @Operation(summary = "개인 과제 날짜로 여러개 조회하기")
    public ResponseEntity<List<SelfAssignmentResponse>> getAssignmentsByDate(
            @Schema(description = "날짜 형식은 'yyyy-mm-dd'로 해주세요", example = "2024-04-30")
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
            @RequestParam("date") LocalDate date) {
        return ResponseEntity.ok(selfAssignmentService.getSelfAssignmentsByDate(date));
    }

    @DeleteMapping("/{assignmentId}")
    @Operation(summary = "개인 과제 삭제하기")
    public ResponseEntity<Void> deleteSelfAssignment(@PathVariable Long assignmentId) {
        selfAssignmentService.deleteSelfAssignment(assignmentId);
        return ResponseEntity.ok().body(null);
    }
}
