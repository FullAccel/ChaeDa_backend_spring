package Chaeda_spring.domain.image.controller;

import Chaeda_spring.domain.image.dto.PresignedUrlRequest;
import Chaeda_spring.domain.image.dto.PresignedUrlResponse;
import Chaeda_spring.domain.image.dto.UploadCompleteRequest;
import Chaeda_spring.domain.image.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    @Operation(summary = "이미지 여러장 보내기")
    public ResponseEntity<String> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        for (MultipartFile file : files) {
            try {
                fileService.uploadFile(file);
            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
        }
        return ResponseEntity.ok("Files uploaded successfully");
    }

    @PostMapping("/presigned-url/{memberId}")
    @Operation(summary = "파일을 업로드할 presigned-url 요청")
    public ResponseEntity<PresignedUrlResponse> createPresignedUrl(@PathVariable Long memberId, @Valid @RequestBody PresignedUrlRequest request) {
        return ResponseEntity.ok(fileService.generatePresignedUrl(memberId, request));
    }

    @PostMapping("/presigned-url/complete/{memberId}")
    @Operation(summary = "s3에 업로드 완료 후 완료 요청 보내기")
    public ResponseEntity<Boolean> uploadImageComplete(@PathVariable Long memberId, @Valid @RequestBody UploadCompleteRequest request) {
        boolean isSaveComplete = fileService.uploadImageComplete(memberId, request);
        return ResponseEntity.ok(isSaveComplete);
    }

    @GetMapping("/display")
    @Operation(summary = "이미지 파일 읽어올 url 요청")
    public ResponseEntity<List<String>> getDisplayUrl(@RequestParam("filenames") String[] filenames) {
        return ResponseEntity.ok(fileService.getFileUrl(filenames));
    }

}

