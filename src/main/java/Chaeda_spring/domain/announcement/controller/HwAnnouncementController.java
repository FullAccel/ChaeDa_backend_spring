package Chaeda_spring.domain.announcement.controller;

import Chaeda_spring.domain.announcement.dto.HwAnnouncementContentDto;
import Chaeda_spring.domain.announcement.dto.HwAnnouncementRequestDto;
import Chaeda_spring.domain.announcement.dto.HwAnnouncementSimpleResponseDto;
import Chaeda_spring.domain.announcement.service.HwAnnouncementService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification/homework")
public class HwAnnouncementController {

    private final HwAnnouncementService hwNotificationService;
    @PostMapping("/{classId}/{memberId}")
    @Operation(summary = "선생님이 숙제 공지하기")
    public ResponseEntity<?> postHomework(@PathVariable Long classId, @PathVariable Long memberId, @RequestBody HwAnnouncementRequestDto requestDto) {
        Long id = hwNotificationService.uploadHomeworkNotification(classId, memberId,requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("/teacher/{memberId}")
    @Operation(summary = "선생님이 숙제 공지한 리스트 가져오기")
    public ResponseEntity<List<HwAnnouncementSimpleResponseDto>> getHomeworkToTeacher(@PathVariable Long memberId){
        List<HwAnnouncementSimpleResponseDto> hwNotificationList = hwNotificationService.getHwToTeacher(memberId);
        return ResponseEntity.ok(hwNotificationList);
    }
    
    @GetMapping("/{hwAnnouncementId}")
    @Operation(summary = "숙제 공지 세부 내용 가져오기", description = "해당 숙제 공지의 용량이 큰 데이터들을 가져옵니다")
    public ResponseEntity<HwAnnouncementContentDto> getHomeworkContent(@PathVariable Long hwAnnouncementId){
        HwAnnouncementContentDto hwNotification = hwNotificationService.getHwContent(hwAnnouncementId);
        return ResponseEntity.ok(hwNotification);
    }

}
