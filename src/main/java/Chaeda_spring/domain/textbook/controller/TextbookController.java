package Chaeda_spring.domain.textbook.controller;

import Chaeda_spring.domain.class_group.dto.ClassGroupResponseDto;
import Chaeda_spring.domain.textbook.dto.TextbookResponseDto;
import Chaeda_spring.domain.textbook.service.TextbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/textbook")
public class TextbookController {

    private final TextbookService textbookService;

    @GetMapping("/list")
    public ResponseEntity<List<TextbookResponseDto>> getClassGroupList() {
        return ResponseEntity.ok().body(textbookService.getTextbookList());
    }
}
