package Chaeda_spring.domain.textbook.controller;

import Chaeda_spring.domain.textbook.dto.TextbookResponse;
import Chaeda_spring.domain.textbook.dto.TextbookUrlResponse;
import Chaeda_spring.domain.textbook.dto.UploadTextbookFileRequest;
import Chaeda_spring.domain.textbook.service.TextbookService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/textbook")
public class TextbookController {

    private final TextbookService textbookService;

    @GetMapping("/list")
    @Operation(summary = "DB에 등록된 교재 목록 가져오기")
    public ResponseEntity<List<TextbookResponse>> getClassGroupList() {
        return ResponseEntity.ok().body(textbookService.getTextbookList());
    }

    @PostMapping("/presigned-url")
    @Operation(summary = "한 권의 교재를 업로드할 presigned-url 요청")
    public ResponseEntity<TextbookUrlResponse> createPresignedUrl(
            @Valid @RequestBody UploadTextbookFileRequest request) {
        return ResponseEntity.ok(textbookService.createTextbookUploadUrl(request));
    }

    @PostMapping("/uploadCompleted")
    @Operation(summary = "교재 PDF S3 업로드 완료 요청")
    public ResponseEntity<Void> uploadCompleted(@RequestBody UploadTextbookFileRequest request) {
        textbookService.saveTextBook(request);
        return ResponseEntity.ok().body(null);
    }


}
