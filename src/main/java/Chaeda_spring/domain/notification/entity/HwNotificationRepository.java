package Chaeda_spring.domain.notification.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HwNotificationRepository extends JpaRepository<HomeworkNotification, Long> {

    List<HomeworkNotification> findAllByDeadLineDate(LocalDate date);
}
