package Chaeda_spring.domain.notification.controller;

import Chaeda_spring.domain.notification.dto.HwNotificationRequestDto;
import Chaeda_spring.domain.notification.dto.HwNotificationResponseDto;
import Chaeda_spring.domain.notification.service.HwNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification/homework")
public class HwNotificationController {

    private final HwNotificationService hwNotificationService;
    @PostMapping("/{classId}/{memberId}")
    public ResponseEntity<?> postHomework(@PathVariable Long classId, @PathVariable Long memberId, @RequestBody HwNotificationRequestDto requestDto) {
        Long id = hwNotificationService.uploadHomeworkNotification(classId, memberId,requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("/teacher/{memberId}")
    public ResponseEntity<List<HwNotificationResponseDto>> getHomeworkToTeacher(@PathVariable Long memberId){
        List<HwNotificationResponseDto> hwNotificationList = hwNotificationService.getHwToTeacher(memberId);
        return ResponseEntity.ok(hwNotificationList);
    }

    @GetMapping("/student/{memberId}")
    public ResponseEntity<List<HwNotificationResponseDto>> getHomeworkToStudent(@PathVariable Long memberId){
        List<HwNotificationResponseDto> hwNotificationList = hwNotificationService.getHwToStudent(memberId);
        return ResponseEntity.ok(hwNotificationList);
    }
}
