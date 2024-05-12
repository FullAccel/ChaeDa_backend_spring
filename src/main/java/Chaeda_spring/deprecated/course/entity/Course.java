package Chaeda_spring.deprecated.course.entity;

import Chaeda_spring.deprecated.class_group.entity.ClassGroup;
import Chaeda_spring.domain.member.entity.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Deprecated
@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    private ClassGroup classGroup;

}
