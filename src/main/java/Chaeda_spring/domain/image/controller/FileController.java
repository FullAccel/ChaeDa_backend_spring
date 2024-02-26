package Chaeda_spring.domain.image.controller;

import Chaeda_spring.domain.image.dto.ImageUploadRequest;
import Chaeda_spring.domain.image.dto.PresignedUrlResponse;
import Chaeda_spring.domain.image.dto.UploadCompleteRequest;
import Chaeda_spring.domain.image.service.FileService;
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
public class FileController {

    private final FileService fileService;

    @PostMapping(value = "/upload/{memberId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "이미지 직접 서버로 보내서 저장하기")
    public ResponseEntity<ArrayList<String>> uploadFiles(
            @Parameter(description = "압로드할 이미지 파일 이름은 'homework_thumbnail-1' 같이 {이미지타입}-{순서}로 해주세요")
            @RequestPart("files") MultipartFile[] files,
            @RequestParam Long memberId) {
        return ResponseEntity.ok(fileService.uploadFile(files, memberId));
    }

    @PostMapping("/presigned-url/{memberId}")
    @Operation(summary = "파일을 업로드할 presigned-url 요청")
    public ResponseEntity<PresignedUrlResponse> createPresignedUrl(@PathVariable Long memberId, @Valid @RequestBody ImageUploadRequest request) {
        return ResponseEntity.ok(fileService.generatePresignedUrl(memberId, request));
    }

    @PostMapping("/presigned-url/complete/{memberId}")
    @Operation(summary = "s3에 업로드 완료 후 완료 요청 보내기")
    public ResponseEntity<Boolean> uploadImageComplete(@PathVariable Long memberId, @Valid @RequestBody UploadCompleteRequest request) {
        boolean isSaveComplete = fileService.uploadImageComplete(memberId, request);
        return ResponseEntity.ok(isSaveComplete);
    }

    @PostMapping("/display/{memberId}")
    @Operation(summary = "이미지 파일 읽어올 url 요청")
    public ResponseEntity<List<String>> getDisplayUrl(@PathVariable Long memberId, @RequestBody List<UploadCompleteRequest> requests) {
        return ResponseEntity.ok(fileService.getFileUrl(memberId, requests));
    }

}

