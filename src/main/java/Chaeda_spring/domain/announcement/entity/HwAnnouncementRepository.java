package Chaeda_spring.domain.announcement.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HwAnnouncementRepository extends JpaRepository<HomeworkAnnouncement, Long> {

    List<HomeworkAnnouncement> findAllByDeadLineDate(LocalDate date);
}
