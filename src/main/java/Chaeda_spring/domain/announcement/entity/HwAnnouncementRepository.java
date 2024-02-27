package Chaeda_spring.domain.announcement.entity;

import Chaeda_spring.domain.class_group.entity.ClassGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HwAnnouncementRepository extends JpaRepository<HwAnnouncement, Long> {

    List<HwAnnouncement> findAllByDeadLineDateAndClassGroup(LocalDate date, ClassGroup classGroup);
}
