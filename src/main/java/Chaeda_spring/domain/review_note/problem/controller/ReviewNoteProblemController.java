package Chaeda_spring.domain.review_note.problem.controller;

import Chaeda_spring.domain.member.entity.Member;
import Chaeda_spring.domain.review_note.problem.dto.ReviewNoteProblemInfo;
import Chaeda_spring.domain.review_note.problem.dto.ReviewNoteProblemResponse;
import Chaeda_spring.domain.review_note.problem.service.ReviewNoteProblemService;
import Chaeda_spring.global.annotation.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review-note")
public class ReviewNoteProblemController {

    private final ReviewNoteProblemService reviewNoteProblemService;


    @PostMapping("/problem")
    @Operation(summary = "오답 저장소에 문제 추가하기", description = "먼저 이미지를 s3에 presigned-url을 통해 저장하고 나온 imageKey와 imageFileExtension을 보내주세요")
    public ResponseEntity<Void> createReviewNoteProblem(
            @AuthUser Member member,
            @RequestBody ReviewNoteProblemInfo reviewNoteProblemInfo) {
        reviewNoteProblemService.createReviewNoteProblem(member, reviewNoteProblemInfo);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/problem/list")
    @Operation(summary = "오답 저장소에 저장된 문제 가져오기")
    public ResponseEntity<List<ReviewNoteProblemResponse>> getProblemsInInCorrectStorage(
            @AuthUser Member member) {
        return ResponseEntity.ok(reviewNoteProblemService.getProblemsInInCorrectStorage(member));
    }

    @DeleteMapping("/problem")
    @Operation(summary = "오답 저장소에서 문제 삭제하기")
    public ResponseEntity<Long> deleteProblemFromStorage(
            @AuthUser Member member,
            @Schema(example = "[1,3,6,7]")
            @RequestBody List<Long> reviewNoteProblemIds) {
        reviewNoteProblemService.deleteProblemFromStorage(member, reviewNoteProblemIds);
        return ResponseEntity.ok().body(null);
    }
}
