package Chaeda_spring.domain.submission.assignment.controller;

import Chaeda_spring.domain.member.entity.Member;
import Chaeda_spring.domain.submission.assignment.dto.AssignmentSubmissionRequest;
import Chaeda_spring.domain.submission.assignment.dto.ProblemNumScopeResponse;
import Chaeda_spring.domain.submission.assignment.service.AssignmentSubmissionService;
import Chaeda_spring.global.annotation.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/submission/self-assignments")
public class SubmissionSelfAssignmentController {

    private final AssignmentSubmissionService assignmentSubmissionService;

    @PostMapping("/{assignmentId}")
    @Operation(summary = "개인 과제 결과 제출하기")
    public ResponseEntity<Void> submitAssignment(
            @AuthUser Member member,
            @PathVariable Long assignmentId,
            @RequestBody AssignmentSubmissionRequest assignmentSubmissionRequest
    ) {
        assignmentSubmissionService.updateMathProblemRecords(member, assignmentId, assignmentSubmissionRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{assignmentId}")
    @Operation(summary = "개인 과제의 페이지별 문제 번호 리스트 조회하기")
    public ResponseEntity<List<ProblemNumScopeResponse>> getProblemNumListById(
            @PathVariable Long assignmentId
    ) {
        return ResponseEntity.ok(assignmentSubmissionService.getProblemNumScopes(assignmentId));
    }
}
