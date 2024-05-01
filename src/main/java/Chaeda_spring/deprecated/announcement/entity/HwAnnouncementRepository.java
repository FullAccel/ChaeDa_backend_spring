package Chaeda_spring.deprecated.announcement.entity;

import Chaeda_spring.deprecated.class_group.entity.ClassGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HwAnnouncementRepository extends JpaRepository<HwAnnouncement, Long> {

    List<HwAnnouncement> findAllByDeadLineDateAndClassGroup(LocalDate date, ClassGroup classGroup);
}
