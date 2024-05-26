package Chaeda_spring.domain.File.controller;

import Chaeda_spring.domain.File.dto.ImageResponse;
import Chaeda_spring.domain.File.dto.UploadImageCompleteRequest;
import Chaeda_spring.domain.File.dto.UploadImageRequest;
import Chaeda_spring.domain.File.service.ImageService;
import Chaeda_spring.domain.member.entity.Member;
import Chaeda_spring.global.annotation.AuthUser;
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

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "이미지 직접 서버로 보내서 저장하기")
    public ResponseEntity<ArrayList<String>> uploadFiles(
            @Parameter(description = "압로드할 이미지 파일 이름은 'homework_thumbnail-1' 같이 {이미지타입}-{순서}로 해주세요."
                    +
                    "\n이미지 타입은 소문자로 homework_thumbnail, announcement_thumbnail, member_profile 중 하나입니다.")
            @RequestPart("files") MultipartFile[] files) {
        return ResponseEntity.ok(imageService.uploadFile(files));
    }

    @PostMapping("/presigned-url")
    @Operation(summary = "한 장의 이미지를 업로드할 presigned-url 요청")
    public ResponseEntity<ImageResponse> createPresignedUrl(
            @Valid @RequestBody UploadImageRequest request) {
        return ResponseEntity.ok(imageService.getImageUploadUrl(request));
    }

    @PostMapping("/presigned-url/submission")
    @Operation(summary = "여러 장의 이미지를 업로드할 presigned-url 요청")
    public ResponseEntity<List<ImageResponse>> createPresignedUrlList(
            @PathVariable Long memberId,
            @Valid @RequestBody List<UploadImageRequest> requests) {
        return ResponseEntity.ok(imageService.getImageUploadUrlList(requests));
    }

    @PostMapping("/display")
    @Operation(summary = "이미지 파일 읽어올 url 요청")
    public ResponseEntity<List<ImageResponse>> getDisplayUrl(@RequestBody List<UploadImageCompleteRequest> requests) {
        return ResponseEntity.ok(imageService.getImageReadUrl(requests));
    }

    @PostMapping("/upload-complete/{memberId}")
    @Operation(summary = "오답 노트 문제 이미지 s3에 업로드 완료 후 완료 요청 보내기",
            description = "오답 노트 문제 사진을 DB에 업로드합니다")
    public ResponseEntity<Void> uploadImageComplete(
            @AuthUser Member member,
            @Valid @RequestBody List<UploadImageCompleteRequest> requests) {
        imageService.uploadImageComplete(member, requests);
        return ResponseEntity.ok().body(null);
    }

}

