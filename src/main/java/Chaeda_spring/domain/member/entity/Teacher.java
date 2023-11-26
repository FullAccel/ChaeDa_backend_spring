package Chaeda_spring.domain.member.entity;

import Chaeda_spring.domain.class_group.entity.ClassGroup;
import Chaeda_spring.domain.notification.entity.HomeworkNotification;
import jakarta.persistence.*;
import lombok.Builder;
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
public class Teacher extends Member{

    private String subject;

    private String notes;

    @OneToMany(mappedBy = "teacher")
    private List<ClassGroup> classGroupList = new ArrayList<>();

    @OneToMany(mappedBy = "teacher")
    private List<HomeworkNotification> homeworkNotificationList = new ArrayList<>();

    public Teacher(String name, String email, String gender, String phoneNumber, String address, String profileUrl, String subject, String notes) {
        super(name, email, gender, phoneNumber, address, profileUrl);
        this.subject = subject;
        this.notes = notes;
    }
}
