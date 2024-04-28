package Chaeda_spring.domain.member.entity;

import Chaeda_spring.domain.image.entity.Image;
import Chaeda_spring.domain.BaseTimeEntity;
import Chaeda_spring.domain.member.dto.SignUpRequest;
import Chaeda_spring.domain.member.dto.SignUpRequestForStudent;
import Chaeda_spring.domain.member.dto.SignUpRequestForTeacher;
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
public abstract class Member extends BaseTimeEntity {

    @OneToOne
    @JoinColumn(name = "image_id")
    Image image;
    @Id
    @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @Column(nullable = false)
    private String loginId;

    //    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String phoneNumber;

    private String address;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public Member(String name, String email, String gender, String phoneNumber, String address, Role role) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }

    public static Member createSignUpMember(SignUpRequest signUpRequest, String password) {
        if (signUpRequest instanceof SignUpRequestForStudent) {
            SignUpRequestForStudent request = (SignUpRequestForStudent) signUpRequest;
            return Student.builder()
                    .name(request.name())
                    .email(request.email())
                    .gender(request.gender())
                    .phoneNumber(request.phoneNumber())
                    .schoolName(request.schoolName())
                    .loginId(request.loginId())
                    .role(request.role())
                    .grade(request.grade())
                    .password(password)
                    .build();
        }
        SignUpRequestForTeacher request = (SignUpRequestForTeacher) signUpRequest;
        return Teacher.builder()
                .name(request.name())
                .email(request.email())
                .gender(request.gender())
                .phoneNumber(request.phoneNumber())
                .loginId(request.loginId())
                .role(request.role())
                .password(password)
                .build();
    }
}
