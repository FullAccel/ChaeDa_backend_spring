package Chaeda_spring.domain.announcement.controller;

import Chaeda_spring.domain.announcement.dto.HwAnnouncementContentDto;
import Chaeda_spring.domain.announcement.dto.HwAnnouncementRequest;
import Chaeda_spring.domain.announcement.dto.HwAnnouncementResponseDto;
import Chaeda_spring.domain.announcement.service.HwAnnouncementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification/homework")
public class HwAnnouncementController {

    private final HwAnnouncementService hwNotificationService;

    @PostMapping("/{classId}/{memberId}")
    @Operation(summary = "선생님이 숙제 공지하기")
    public ResponseEntity<Long> postHomework(@PathVariable Long classId, @PathVariable Long memberId, @RequestBody HwAnnouncementRequest requestDto) {
        Long id = hwNotificationService.uploadHomeworkNotification(classId, memberId, requestDto);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/teacher/{memberId}")
    @Operation(summary = "선생님이 숙제 공지한 리스트 가져오기")
    public ResponseEntity<List<HwAnnouncementResponseDto>> getHomeworkByTeacher(@PathVariable Long memberId) {
        List<HwAnnouncementResponseDto> hwNotificationList = hwNotificationService.getHwToTeacher(memberId);
        return ResponseEntity.ok(hwNotificationList);
    }

    @GetMapping("/{hwAnnouncementId}")
    @Operation(summary = "숙제 공지 세부 내용 가져오기", description = "해당 숙제 공지의 본문과 잉미지 링크를 가져옵니다")
    public ResponseEntity<HwAnnouncementContentDto> getHomeworkContent(@PathVariable Long hwAnnouncementId) {
        HwAnnouncementContentDto hwNotification = hwNotificationService.getHwContent(hwAnnouncementId);
        return ResponseEntity.ok(hwNotification);
    }

    @GetMapping("student/{memberId}")
    @Operation(summary = "날짜 별로 숙제 리스트를 가져오기", description = "해당 날짜의 숙제 리스트만 가져옵니다")
    public ResponseEntity<String> getHomeworkListByDate(
            @PathVariable Long memberId,
            @Parameter(description = "날짜 형식은 'yyyy-mm-dd'로 해주세요")
            @RequestParam("date") String date
    ) {
        return null;
    }

}
