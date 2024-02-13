package Chaeda_spring.domain.submission.controller;

import Chaeda_spring.domain.submission.dto.SubmissionImageRequestDto;
import Chaeda_spring.domain.submission.dto.SubmissionResponseDto;
import Chaeda_spring.domain.submission.service.SubmissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/submission")
public class SubmissionController {

    private final SubmissionService submissionService;

    @GetMapping("/student/{memberId}")
    @Operation(summary = "특정 날짜의 학생에게 할당된 숙제 가져오기")
    @Parameter(name = "memberId", description = "학생 아이디(dummy 범위 4~6)")
    public ResponseEntity<List<SubmissionResponseDto>> getHomeworkToStudent(
            @PathVariable Long memberId,
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        List<SubmissionResponseDto> hwNotificationList = submissionService.getHwToStudent(memberId, date);
        return ResponseEntity.ok(hwNotificationList);
    }

    @PostMapping ("/{memberId}/{homeworkId}")
    @Operation(summary = "FastAPI에서 이미지 받아오기")
    public ResponseEntity<Void> getHomeworkToStudent(
            @PathVariable Long memberId,
            @PathVariable Long homeworkId,
            @RequestBody SubmissionImageRequestDto dto){
        submissionService.storeS3Url(memberId, homeworkId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
