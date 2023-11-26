package Chaeda_spring.domain.submission.controller;

import Chaeda_spring.domain.announcement.dto.HwAnnouncementSimpleResponseDto;
import Chaeda_spring.domain.submission.dto.SubmissionResponseDto;
import Chaeda_spring.domain.submission.service.SubmissionService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
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
}
