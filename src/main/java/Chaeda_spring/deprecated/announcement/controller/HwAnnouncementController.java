package Chaeda_spring.deprecated.announcement.controller;

import Chaeda_spring.deprecated.announcement.dto.HwAnnounceSummaryResponse;
import Chaeda_spring.deprecated.announcement.dto.HwAnnouncementRequest;
import Chaeda_spring.deprecated.announcement.dto.HwAnnouncementResponse;
import Chaeda_spring.deprecated.announcement.service.HwAnnouncementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Deprecated
@RestController
@RequiredArgsConstructor
@RequestMapping("/announcement/homework")
public class HwAnnouncementController {

    private final HwAnnouncementService hwAnnouncementService;

    @PostMapping("/{classId}/{memberId}")
    @Operation(summary = "(선생님용) 선생님이 숙제 공지하기")
    public ResponseEntity<Long> postHomework(@PathVariable Long classId, @PathVariable Long memberId, @RequestBody HwAnnouncementRequest requestDto) {
        Long id = hwAnnouncementService.uploadHwAnnouncement(classId, memberId, requestDto);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/teacher/{memberId}")
    @Operation(summary = "(선생님용) 선생님 자신이 숙제 공지한 리스트 가져오기")
    public ResponseEntity<List<HwAnnouncementResponse>> getHomeworkByTeacher(@PathVariable Long memberId) {
        List<HwAnnouncementResponse> hwNotificationList = hwAnnouncementService.getHwToTeacher(memberId);
        return ResponseEntity.ok(hwNotificationList);
    }

    @GetMapping("/{hwAnnouncementId}")
    @Operation(summary = "(공용) 특정 숙제 공지 가져오기", description = "해당 숙제 공지의 본문과 이미지 링크 및 필요한 모든 내용을 가져옵니다")
    public ResponseEntity<HwAnnouncementResponse> getHomeworkContent(@PathVariable Long hwAnnouncementId) {
        HwAnnouncementResponse hwNotification = hwAnnouncementService.getHwContent(hwAnnouncementId);
        return ResponseEntity.ok(hwNotification);
    }

    @GetMapping("student/{memberId}")
    @Operation(summary = "(학생용) 마감 날짜 기준 숙제 리스트를 가져오기", description = "해당 날짜의 숙제 리스트만 가져옵니다")
    public ResponseEntity<List<HwAnnounceSummaryResponse>> getHomeworkSummaryListByDate(
            @PathVariable Long memberId,
            @Parameter(description = "날짜 형식은 'yyyy-mm-dd'로 해주세요")
            @RequestParam("date") LocalDate date
    ) {
        return ResponseEntity.ok(hwAnnouncementService.getHomeworkSummaryListByDate(memberId, date));
    }

}
