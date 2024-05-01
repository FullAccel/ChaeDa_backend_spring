package Chaeda_spring.domain.submission.assignment.controller;

import Chaeda_spring.domain.submission.assignment.dto.AssignmentSubmissionRequest;
import Chaeda_spring.domain.submission.assignment.service.AssignmentSubmissionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/submission/self-assignments")
public class SubmissionSelfAssignmentController {

    private final AssignmentSubmissionService assignmentSubmissionService;

    @PostMapping("/{assignmentId}")
    @Operation(summary = "개인 과제 결과 제출하기")
    public ResponseEntity<Void> submitAssignment(
            @PathVariable Long assignmentId,
            @RequestBody AssignmentSubmissionRequest assignmentSubmissionRequest
    ) {
        assignmentSubmissionService.updateMathProblemRecords(assignmentId, assignmentSubmissionRequest);
        return ResponseEntity.noContent().build();
    }
}
