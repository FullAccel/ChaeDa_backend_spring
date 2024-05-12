package Chaeda_spring.global.utils;

import Chaeda_spring.DatabaseCleaner;
import Chaeda_spring.domain.member.entity.MemberRepository;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.global.constant.Grade;
import Chaeda_spring.global.constant.Role;
import Chaeda_spring.global.security.CustomUserDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class MemberUtilsTest {

    @Autowired
    private MemberUtils memberUtils;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private DatabaseCleaner databaseCleaner;

    @BeforeEach
    void setUp() {
        databaseCleaner.execute();
    }

    @Test
    void 현재_로그인한_회원_정보를_정상적으로_반환한다() {
        UserDetails userDetails = CustomUserDetails.forJwt(1L, Role.STUDENT);
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Student student = Student.builder()
                .name("Jane Doe")
                .email("jane@example.com")
                .gender("Female")
                .role(Role.STUDENT) // Assuming Role contains an enum STUDENT
                .phoneNumber("123-456-7890")
                .address("123 Main Street")
                .schoolName("XYZ High School")
                .parentPhoneNum("234-567-8901")
                .homePhoneNum("345-678-9012")
                .grade(Grade.HIGH_3)
                .notes("Some notes")
                .build();

        Student savedStudent = memberRepository.save(student);
        Student currentStudent = (Student) memberUtils.getCurrentMember();

        Assertions.assertEquals(savedStudent.getId(), currentStudent.getId());
    }

}
