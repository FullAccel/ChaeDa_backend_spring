package Chaeda_spring.domain.submission.assignment.service;


import Chaeda_spring.DatabaseCleaner;
import Chaeda_spring.domain.Problem.math.MathProblem;
import Chaeda_spring.domain.Problem.math.MathProblemRepository;
import Chaeda_spring.domain.assignment.entity.SelfAssignment;
import Chaeda_spring.domain.assignment.entity.SelfAssignmentRepository;
import Chaeda_spring.domain.member.entity.Member;
import Chaeda_spring.domain.member.entity.MemberRepository;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.statistics.entity.problem_type_statistics.AccumulatedStatisticsForChapterRepository;
import Chaeda_spring.domain.statistics.entity.problem_type_statistics.AccumulatedStatisticsForSubconceptRepository;
import Chaeda_spring.domain.statistics.entity.problem_type_statistics.SubconceptStatisticsForMonthRepository;
import Chaeda_spring.domain.statistics.entity.problem_type_statistics.SubconceptStatisticsForWeekRepository;
import Chaeda_spring.domain.statistics.entity.solvedNum.SolvedNumForDayRepository;
import Chaeda_spring.domain.statistics.entity.solvedNum.SolvedNumForMonthRepository;
import Chaeda_spring.domain.statistics.entity.solvedNum.SolvedNumForWeekRepository;
import Chaeda_spring.domain.submission.assignment.dto.AssignmentSubmissionRequest;
import Chaeda_spring.domain.submission.assignment.dto.WrongProblemListPerPageRequest;
import Chaeda_spring.domain.submission.assignment.entity.WrongProblemRecordRepository;
import Chaeda_spring.domain.textbook.entity.Textbook;
import Chaeda_spring.domain.textbook.entity.TextbookRespository;
import Chaeda_spring.global.constant.DifficultyLevel;
import Chaeda_spring.global.constant.Grade;
import Chaeda_spring.global.constant.Role;
import Chaeda_spring.global.constant.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Year;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ActiveProfiles("test")
public class AssignmentSubmissionServiceTest {

    @Autowired
    private SelfAssignmentRepository selfAssignmentRepository;
    @Autowired
    private MathProblemRepository mathProblemRepository;
    @Autowired
    private WrongProblemRecordRepository wrongProblemRecordRepository;
    @Autowired
    private SolvedNumForDayRepository solvedNumForDayRepository;
    @Autowired
    private SolvedNumForWeekRepository solvedNumForWeekRepository;
    @Autowired
    private SolvedNumForMonthRepository solvedNumForMonthRepository;

    @Autowired
    private SubconceptStatisticsForWeekRepository subconceptStatisticsForWeekRepository;
    @Autowired
    private SubconceptStatisticsForMonthRepository subconceptStatisticsForMonthRepository;
    @Autowired
    private AccumulatedStatisticsForSubconceptRepository accumulatedStatisticsForSubconceptRepository;
    @Autowired
    private AccumulatedStatisticsForChapterRepository accumulatedStatisticsForChapterRepository;
    @Autowired
    private DatabaseCleaner databaseCleaner;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TextbookRespository textbookRespository;
    @Autowired
    private AssignmentSubmissionService assignmentSubmissionService;

    @BeforeEach
    void setUp() {
        databaseCleaner.execute();

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

        Textbook textbook = Textbook.builder()
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
                .build();
        textbookRespository.save(textbook);

        SelfAssignment selfAssignment = SelfAssignment.builder()
                .title("Test Assignment")
                .startPage(1)
                .endPage(2)
                .targetDate(LocalDate.now())
                .textbook(textbook)
                .student(student)
                .build();
        selfAssignmentRepository.save(selfAssignment);

        Map<Integer, List<String>> pageToProblemNumbers = new HashMap<>();
        pageToProblemNumbers.put(1, Arrays.asList("1.1", "1.2", "1.3"));
        pageToProblemNumbers.put(2, Arrays.asList("2.1", "2.2", "2.3"));

        pageToProblemNumbers.forEach((pageNumber, problemNumbers) -> {
            problemNumbers.stream()
                    .map(problemNumber -> MathProblem.builder()
                            .problemNumber(problemNumber)
                            .pageNumber(pageNumber)
                            .textbook(textbook)
                            .build())
                    .forEach(mathProblemRepository::save);
        });
    }

    @Nested
    class 과제_제출시 {

        HashMap<String, DifficultyLevel> wrongProblems1 = new HashMap<>() {{
            put("1.1", DifficultyLevel.HIGH);
            put("1.3", DifficultyLevel.MEDIUM);
        }};

        WrongProblemListPerPageRequest wrongProblemListPerPageRequest1 =
                new WrongProblemListPerPageRequest(1, wrongProblems1);

        HashMap<String, DifficultyLevel> wrongProblems2 = new HashMap<>() {{
            put("2.2", DifficultyLevel.LOW);
        }};

        WrongProblemListPerPageRequest wrongProblemListPerPageRequest2 =
                new WrongProblemListPerPageRequest(2, wrongProblems2);

        List<WrongProblemListPerPageRequest> wrongProblemListPerPageRequests = Arrays.asList(wrongProblemListPerPageRequest1, wrongProblemListPerPageRequest2);
        AssignmentSubmissionRequest request = new AssignmentSubmissionRequest(wrongProblemListPerPageRequests);

        Member member = memberRepository.findById(1L).get();

        @BeforeEach
        void runUpdateMathProblemRecords() {
            SelfAssignment selfAssignment = selfAssignmentRepository.findById(1L).get();
            assignmentSubmissionService.updateMathProblemRecords(member, selfAssignment.getId(), request);
        }

        @Nested
        class 기간별로_문제_수_만큼_횟수가_증가해야합니다 {

            @Test
            void 일별_푼_문제_수가_올바르게_업데이트되어야합니다() {
                //given


            }
        }
    }

}
