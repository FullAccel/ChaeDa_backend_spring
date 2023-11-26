package Chaeda_spring.domain.member.entity;

import Chaeda_spring.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Member extends BaseTimeEntity {

    @Id
    @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String phoneNumber;

    private String address;

    private String profileUrl;

    public Member(String name, String email, String gender, String phoneNumber, String address, String profileUrl) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.profileUrl = profileUrl;
    }
}
