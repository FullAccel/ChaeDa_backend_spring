package Chaeda_spring.domain.member.entity;

import Chaeda_spring.deprecated.announcement.entity.HwAnnouncement;
import Chaeda_spring.deprecated.class_group.entity.ClassGroup;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("Teacher")
public class Teacher extends Member {

    private String subject;

    private String notes;

    @OneToMany(mappedBy = "teacher")
    private List<ClassGroup> classGroupList = new ArrayList<>();

    @OneToMany(mappedBy = "teacher")
    private List<HwAnnouncement> homeworkNotificationList = new ArrayList<>();

    public Teacher(String name, String email, String gender, String phoneNumber, Role role, String address, String subject, String notes) {
        super(name, email, gender, phoneNumber, address, role);
        this.subject = subject;
        this.notes = notes;
    }
}
