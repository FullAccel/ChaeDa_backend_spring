package Chaeda_spring.domain.image.controller;

import Chaeda_spring.domain.image.dto.PresignedUrlRequest;
import Chaeda_spring.domain.image.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/files")
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
    public ResponseEntity<String> createPresignedUrl(@PathVariable Long memberId, @RequestBody PresignedUrlRequest request) {
        return ResponseEntity.ok(fileService.generatePresignedUrl(memberId, request));
    }
}

