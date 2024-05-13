package Chaeda_spring.domain.assignment.service;

import Chaeda_spring.DatabaseCleaner;
import Chaeda_spring.domain.assignment.dto.SelfAssignmentRequest;
import Chaeda_spring.domain.assignment.dto.SelfAssignmentResponse;
import Chaeda_spring.domain.member.entity.MemberRepository;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.textbook.entity.Textbook;
import Chaeda_spring.domain.textbook.entity.TextbookRespository;
import Chaeda_spring.global.constant.Grade;
import Chaeda_spring.global.constant.Role;
import Chaeda_spring.global.constant.Subject;
import Chaeda_spring.global.security.CustomUserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Year;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class SelfAssignmentServiceTest {

    @Autowired
    DatabaseCleaner databaseCleaner;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private SelfAssignmentService selfAssignmentService;

    @Autowired
    private TextbookRespository textbookRespository;

    @BeforeEach
    void setUp() {
        databaseCleaner.execute();
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

        memberRepository.save(student);
    }


    @Nested
    class 과제_생성할_때 {
        @Test
        void 과제를_올바르게_생성한다() {
            SelfAssignmentRequest request = new SelfAssignmentRequest(
                    "New assignment",
                    1,
                    10,
                    LocalDate.parse("2024-12-01"),
                    1L
            );
            textbookRespository.save(Textbook.builder()
                    .name("My First Textbook")
                    .startPageNum(1)
                    .lastPageNum(100)
                    .publisher("Sample Publisher")
                    .targetGrade(Grade.HIGH_3) // Choose the appropriate Grade
                    .subject(Subject.Math_2) // Choose the appropriate Subject
                    .publishYear(Year.now())
                    .uploadMemberId(123L)
                    .textbookThumbnail("thumbnailPath")
                    .textbookSrcUrl("srcUrlPath")
                    .build());

            SelfAssignmentResponse response = selfAssignmentService.createSelfAssignment(request);

            assertNotNull(response);
            assertEquals(request.title(), response.title());
            assertEquals(request.startPage(), response.startPage());
            assertEquals(request.endPage(), response.endPage());
            assertEquals(request.targetDate(), response.targetDate());
        }
    }

}
