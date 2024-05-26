package Chaeda_spring.domain.review_note.controller;

import Chaeda_spring.domain.File.dto.PresignedUrlResponse;
import Chaeda_spring.domain.member.entity.Member;
import Chaeda_spring.domain.review_note.dto.*;
import Chaeda_spring.domain.review_note.service.ReviewNoteProblemService;
import Chaeda_spring.global.annotation.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review-note")
public class ReviewNoteController {

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

    @PostMapping("/folder")
    @Operation(summary = "오답 폴더 만들기")
    public ResponseEntity<Long> createReviewNoteFolder(
            @AuthUser Member member,
            @RequestBody ReviewNoteProblemIdRequest request) {
        return ResponseEntity.ok(reviewNoteProblemService.createReviewNoteFolder(member, request));
    }

    @PostMapping("/pdf/{reviewNoteFolderId}")
    @Operation(summary = "오답 노트 pdf 만들기")
    public ResponseEntity<Long> createReviewNotePDF(
            @AuthUser Member member,
            @PathVariable Long reviewNoteFolderId
    ) {
        return ResponseEntity.ok(reviewNoteProblemService.createReviewNotePDF(member, reviewNoteFolderId));
    }

    @GetMapping("/folder/{folderId}/problem/list")
    @Operation(summary = "오답 노트 폴더 속 문제 list 가져오기")
    public ResponseEntity<List<ReviewNoteProblemResponse>> getProblemListInFolder(
            @AuthUser Member member,
            @PathVariable Long folderId
    ) {
        return ResponseEntity.ok(reviewNoteProblemService.getProblemListInFolder(member, folderId));
    }

    @GetMapping("/folder/list")
    @Operation(summary = "오답 노트 folder list 가져오기")
    public ResponseEntity<List<ReviewNoteFolderInfo>> getReviewNoteFolderList(
            @AuthUser Member member
    ) {
        return ResponseEntity.ok(reviewNoteProblemService.getReviewNoteFolderList(member));
    }

    @GetMapping("/pdf/list")
    @Operation(summary = "오답 노트 pdf id list 가져오기")
    public ResponseEntity<List<ReviewNotePDFInfo>> getReviewNotePDFList(
            @AuthUser Member member
    ) {
        return ResponseEntity.ok(reviewNoteProblemService.getReviewNotePDFList(member));
    }

    @GetMapping("/pdf/{reviewNotePDFId}")
    @Operation(summary = "오답 노트 pdf 다운로드 url 가져오기")
    public ResponseEntity<PresignedUrlResponse> getReviewNotePDFUrl(
            @AuthUser Member member,
            @PathVariable Long reviewNotePDFId
    ) {
        return ResponseEntity.ok(reviewNoteProblemService.getReviewNotePDFUrl(member, reviewNotePDFId));
    }


}
