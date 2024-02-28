package Chaeda_spring.domain.image.controller;

import Chaeda_spring.domain.image.dto.ImageUploadRequest;
import Chaeda_spring.domain.image.dto.PresignedUrlResponse;
import Chaeda_spring.domain.image.dto.UploadCompleteRequest;
import Chaeda_spring.domain.image.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    @PostMapping(value = "/upload/{memberId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "이미지 직접 서버로 보내서 저장하기")
    public ResponseEntity<ArrayList<String>> uploadFiles(
            @Parameter(description = "압로드할 이미지 파일 이름은 'homework_thumbnail-1' 같이 {이미지타입}-{순서}로 해주세요."
                    +
                    "\n이미지 타입은 소문자로 homework_thumbnail, announcement_thumbnail, member_profile 중 하나입니다.")
            @RequestPart("files") MultipartFile[] files,
            @RequestParam Long memberId) {
        return ResponseEntity.ok(imageService.uploadFile(files, memberId));
    }

    @PostMapping("/presigned-url/{memberId}")
    @Operation(summary = "한 장의 이미지를 업로드할 presigned-url 요청")
    public ResponseEntity<PresignedUrlResponse> createPresignedUrl(
            @PathVariable Long memberId,
            @Valid @RequestBody ImageUploadRequest request) {
        return ResponseEntity.ok(imageService.createFileUploadUrl(memberId, request));
    }

    @PostMapping("/presigned-url/submission/{memberId}")
    @Operation(summary = "여러 장의 이미지를 업로드할 presigned-url 요청")
    public ResponseEntity<List<PresignedUrlResponse>> createPresignedUrl(
            @PathVariable Long memberId,
            @Valid @RequestBody List<ImageUploadRequest> requests) {
        return ResponseEntity.ok(imageService.createFileUploadUrlList(memberId, requests));
    }

    @PostMapping("/display/{memberId}")
    @Operation(summary = "이미지 파일 읽어올 url 요청")
    public ResponseEntity<List<PresignedUrlResponse>> getDisplayUrl(@PathVariable Long memberId, @RequestBody List<UploadCompleteRequest> requests) {
        return ResponseEntity.ok(imageService.getFileReadUrl(memberId, requests));
    }

}

