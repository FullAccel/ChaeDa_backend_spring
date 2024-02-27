package Chaeda_spring.domain.textbook.controller;

import Chaeda_spring.domain.textbook.dto.TextbookResponse;
import Chaeda_spring.domain.textbook.service.TextbookService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
}
