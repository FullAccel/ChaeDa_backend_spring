package Chaeda_spring.domain.review_note.folder_and_pdf.controller;

import Chaeda_spring.domain.File.dto.PresignedUrlResponse;
import Chaeda_spring.domain.member.entity.Member;
import Chaeda_spring.domain.review_note.folder_and_pdf.dto.ReviewNoteFolderInfo;
import Chaeda_spring.domain.review_note.folder_and_pdf.dto.ReviewNotePDFInfo;
import Chaeda_spring.domain.review_note.folder_and_pdf.dto.ReviewNoteProblemIdRequest;
import Chaeda_spring.domain.review_note.folder_and_pdf.service.ReviewNoteFolderService;
import Chaeda_spring.domain.review_note.problem.dto.ReviewNoteProblemResponse;
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
public class ReviewNoteFolderController {

    private final ReviewNoteFolderService reviewNoteFolderService;

    @PostMapping("/folder")
    @Operation(summary = "오답 폴더 만들기")
    public ResponseEntity<Long> createReviewNoteFolder(
            @AuthUser Member member,
            @RequestBody ReviewNoteProblemIdRequest request) {
        return ResponseEntity.ok(reviewNoteFolderService.createReviewNoteFolder(member, request));
    }

    @PutMapping("/folder/{folderId}")
    @Operation(summary = "오답 폴더에 문제 추가하기")
    public ResponseEntity<Void> addProblemToFolder(
            @PathVariable Long folderId,
            @RequestBody List<Long> reviewNoteProblemIds) {
        reviewNoteFolderService.addProblemToFolder(folderId, reviewNoteProblemIds);
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("/folder/{folderId}/problems")
    @Operation(summary = "오답 폴더에서 문제 삭제하기")
    public ResponseEntity<Long> deleteProblemFromFolder(
            @PathVariable Long folderId,
            @Schema(example = "[1,3,6,7]")
            @RequestBody List<Long> reviewNoteProblemIds) {
        reviewNoteFolderService.deleteProblemFromFolder(folderId, reviewNoteProblemIds);
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("/folder/{folderId}")
    @Operation(summary = "오답 폴더 삭제하기")
    public ResponseEntity<Long> deleteFolder(
            @PathVariable Long folderId) {
        reviewNoteFolderService.deleteFolder(folderId);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping("/pdf/{reviewNoteFolderId}")
    @Operation(summary = "오답 노트 pdf 만들기")
    public ResponseEntity<Long> createReviewNotePDF(
            @AuthUser Member member,
            @PathVariable Long reviewNoteFolderId
    ) {
        return ResponseEntity.ok(reviewNoteFolderService.createReviewNotePDF(member, reviewNoteFolderId));
    }

    @GetMapping("/folder/{folderId}/problem/list")
    @Operation(summary = "오답 노트 폴더 속 문제 list 가져오기")
    public ResponseEntity<List<ReviewNoteProblemResponse>> getProblemListInFolder(
            @AuthUser Member member,
            @PathVariable Long folderId
    ) {
        return ResponseEntity.ok(reviewNoteFolderService.getProblemListInFolder(member, folderId));
    }

    @GetMapping("/folder/list")
    @Operation(summary = "오답 노트 folder list 가져오기")
    public ResponseEntity<List<ReviewNoteFolderInfo>> getReviewNoteFolderList(
            @AuthUser Member member
    ) {
        return ResponseEntity.ok(reviewNoteFolderService.getReviewNoteFolderList(member));
    }

    @GetMapping("/pdf/list")
    @Operation(summary = "오답 노트 pdf id list 가져오기")
    public ResponseEntity<List<ReviewNotePDFInfo>> getReviewNotePDFList(
            @AuthUser Member member
    ) {
        return ResponseEntity.ok(reviewNoteFolderService.getReviewNotePDFList(member));
    }

    @GetMapping("/pdf/{reviewNotePDFId}")
    @Operation(summary = "오답 노트 pdf 다운로드 url 가져오기")
    public ResponseEntity<PresignedUrlResponse> getReviewNotePDFUrl(
            @AuthUser Member member,
            @PathVariable Long reviewNotePDFId
    ) {
        return ResponseEntity.ok(reviewNoteFolderService.getReviewNotePDFUrl(member, reviewNotePDFId));
    }


}
