package Chaeda_spring.domain.notification.service;

import Chaeda_spring.domain.notification.dto.HwNotificationRequestDto;
import Chaeda_spring.domain.notification.entity.HomeworkNotification;
import Chaeda_spring.domain.notification.entity.HwNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HwNotificationService {

    private final HwNotificationRepository hwNotificationRepository;

    public Long uploadHomeworkNotification(HwNotificationRequestDto dto) {
        HomeworkNotification hwNotification = dto.toEntity();
        return hwNotificationRepository.save(hwNotification).getId();
    }
}
