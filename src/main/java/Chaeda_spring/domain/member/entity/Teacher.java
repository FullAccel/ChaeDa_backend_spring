package Chaeda_spring.domain.member.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("Teacher")
public class Teacher extends Member{

    private String subject;

    private String notes;

    @Builder
    public Teacher(String name, String email, String gender, String phoneNumber, String address, String profileUrl, String subject, String notes) {
        super(name, email, gender, phoneNumber, address, profileUrl);
        this.subject = subject;
        this.notes = notes;
    }
}
