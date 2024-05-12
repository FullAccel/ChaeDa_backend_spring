package Chaeda_spring.deprecated.announcement.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Deprecated
@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
public class GeneralAnnouncement extends Announcement {

}
