package Chaeda_spring.domain.submission.homework.controller;

import Chaeda_spring.domain.image.dto.ImageResponse;
import Chaeda_spring.domain.image.dto.UploadImageCompleteRequest;
import Chaeda_spring.domain.submission.homework.service.ImageSubmissionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/submission")
public class SubmissionHWController {

    private final ImageSubmissionService imageSubmissionService;

    @Deprecated
    @PostMapping("/upload-complete/{memberId}/{HwAnnouncementId}")
    @Operation(summary = "숙제 이미지 s3에 업로드 완료 후 완료 요청 보내기",
            description = "숙제 사진을 DB에 업로드하고 숙제 제출 상태를 제출 완료로 변경합니다.")
    public ResponseEntity<List<ImageResponse>> uploadImageComplete(
            @PathVariable Long memberId,
            @PathVariable Long HwAnnouncementId,
            @Valid @RequestBody List<UploadImageCompleteRequest> requests) {
        return ResponseEntity.ok(imageSubmissionService.uploadImageCompleteForSubmission(memberId, HwAnnouncementId, requests));
    }

    @Deprecated
    @GetMapping("/image/{memberId}/{hwAnnouncementId}")
    @Operation(summary = "해당 공지 숙제 제출 이미지 가져오기",
            description = "비어있다면 숙제를 제출하지 않은 것 입니다.")
    public ResponseEntity<List<ImageResponse>> getSubmissionImages(
            @PathVariable Long memberId,
            @PathVariable Long hwAnnouncementId) {
        return ResponseEntity.ok(imageSubmissionService.getSubmissionImages(memberId, hwAnnouncementId));
    }

}
