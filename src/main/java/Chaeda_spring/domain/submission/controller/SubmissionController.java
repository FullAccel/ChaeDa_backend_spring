package Chaeda_spring.domain.submission.controller;

import Chaeda_spring.domain.image.dto.PresignedUrlResponse;
import Chaeda_spring.domain.image.dto.UploadCompleteRequest;
import Chaeda_spring.domain.image.service.ImageService;
import Chaeda_spring.domain.submission.dto.SubmissionImageRequestDto;
import Chaeda_spring.domain.submission.service.SubmissionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/submission")
public class SubmissionController {

    private final SubmissionService submissionService;
    private final ImageService imageService;


    @PostMapping("/{memberId}/{homeworkId}")
    @Operation(summary = "FastAPI에서 이미지 받아오기")
    public ResponseEntity<Void> getHomeworkToStudent(
            @PathVariable Long memberId,
            @PathVariable Long homeworkId,
            @RequestBody SubmissionImageRequestDto dto) {
        submissionService.storeS3Url(memberId, homeworkId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/upload-complete/{memberId}/{HwAnnouncementId}")
    @Operation(summary = "숙제 이미지 s3에 업로드 완료 후 완료 요청 보내기",
            description = "숙제 사진을 DB에 업로드하고 숙제 제출 상태를 제출 완료로 변경합니다.")
    public ResponseEntity<List<PresignedUrlResponse>> uploadImageComplete(
            @PathVariable Long memberId,
            @PathVariable Long HwAnnouncementId,
            @Valid @RequestBody List<UploadCompleteRequest> requests) {
        return ResponseEntity.ok(imageService.uploadImageCompleteForSubmission(memberId, HwAnnouncementId, requests));
    }

    //    @GetMapping("/student/{memberId}")
//    @Operation(summary = "특정 날짜의 학생에게 할당된 숙제 가져오기")
//    @Parameter(name = "memberId", description = "학생 아이디(dummy 범위 4~6)")
//    public ResponseEntity<List<SubmissionResponseDto>> getHomeworkToStudent(
//            @PathVariable Long memberId,
//            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
//        List<SubmissionResponseDto> hwNotificationList = submissionService.getHwToStudent(memberId, date);
//        return ResponseEntity.ok(hwNotificationList);
//    }
}
