package Chaeda_spring.deprecated.announcement.entity;

import Chaeda_spring.domain.BaseTimeEntity;
import Chaeda_spring.deprecated.class_group.entity.ClassGroup;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Announcement extends BaseTimeEntity {

    @Id
    @Column(name = "announcement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private ClassGroup classGroup;
}
