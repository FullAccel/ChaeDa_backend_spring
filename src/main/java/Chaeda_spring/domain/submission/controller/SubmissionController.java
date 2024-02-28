package Chaeda_spring.domain.submission.controller;

import Chaeda_spring.domain.image.dto.PresignedUrlResponse;
import Chaeda_spring.domain.image.dto.UploadCompleteRequest;
import Chaeda_spring.domain.image.service.ImageService;
import Chaeda_spring.domain.submission.service.SubmissionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/submission")
public class SubmissionController {

    private final SubmissionService submissionService;
    private final ImageService imageService;

    @PostMapping("/upload-complete/{memberId}/{HwAnnouncementId}")
    @Operation(summary = "숙제 이미지 s3에 업로드 완료 후 완료 요청 보내기",
            description = "숙제 사진을 DB에 업로드하고 숙제 제출 상태를 제출 완료로 변경합니다.")
    public ResponseEntity<List<PresignedUrlResponse>> uploadImageComplete(
            @PathVariable Long memberId,
            @PathVariable Long HwAnnouncementId,
            @Valid @RequestBody List<UploadCompleteRequest> requests) {
        return ResponseEntity.ok(submissionService.uploadImageCompleteForSubmission(memberId, HwAnnouncementId, requests));
    }

    @GetMapping("/image/{memberId}/{hwAnnouncementId}")
    @Operation(summary = "해당 공지 숙제 제출 이미지 가져오기",
            description = "비어있다면 숙제를 제출하지 않은 것 입니다.")
    public ResponseEntity<List<PresignedUrlResponse>> getSubmissionImages(
            @PathVariable Long memberId,
            @PathVariable Long hwAnnouncementId) {
        return ResponseEntity.ok(submissionService.getSubmissionImages(memberId, hwAnnouncementId));
    }

}
