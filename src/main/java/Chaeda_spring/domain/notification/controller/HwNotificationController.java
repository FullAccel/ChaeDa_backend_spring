package Chaeda_spring.domain.notification.controller;

import Chaeda_spring.domain.notification.dto.HwNotificationRequestDto;
import Chaeda_spring.domain.notification.service.HwNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification/homework")
public class HwNotificationController {

    private final HwNotificationService hwNotificationService;
    @PostMapping("")
    public ResponseEntity<?> postHomework(@RequestBody HwNotificationRequestDto requestDto) {
        Long id = hwNotificationService.uploadHomeworkNotification(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }
}
