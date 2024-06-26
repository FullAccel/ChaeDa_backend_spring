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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.TemporalAdjusters;
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
        pageToProblemNumbers.put(3, Arrays.asList("3.1", "3.2", "3.3"));
        pageToProblemNumbers.put(4, Arrays.asList("4.1", "4.2", "4.3"));


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

        //given
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
        AssignmentSubmissionRequest request;

        Member member;

        @BeforeEach
        void runUpdateMathProblemRecords() {
            //when
            request = new AssignmentSubmissionRequest(wrongProblemListPerPageRequests);

            member = memberRepository.findById(1L).get();
            SelfAssignment selfAssignment = selfAssignmentRepository.findById(1L).get();
            assignmentSubmissionService.updateMathProblemRecords(member, selfAssignment.getId(), request);
        }

        @Nested
        class 푼_문제_수가_올바르게_업데이트되어야합니다 {

            @Test
            void 일별() {
                //then
                var solvedNumForDays = solvedNumForDayRepository.findByTodayDateAndStudent(LocalDate.now(), (Student) member);
                Assertions.assertEquals(solvedNumForDays.getSolvedNum(), 6);
            }

            @Test
            void 주별() {
                //then
                var solvedNumForWeek = solvedNumForWeekRepository.findByStartOfWeekAndStudent(
                        LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)), (Student) member);
                Assertions.assertEquals(solvedNumForWeek.getSolvedNum(), 6);
            }

            @Test
            void 월별() {
                //then
                var solvedNumForDays = solvedNumForMonthRepository.findByMonthDateAndStudent(LocalDate.now().withDayOfMonth(1), (Student) member);
                Assertions.assertEquals(solvedNumForDays.getSolvedNum(), 6);
            }

            @Test
            void 추가문제_발생시_일별_주별_월별_정상업데이트() {
                //given
                HashMap<String, DifficultyLevel> wrongProblems = new HashMap<>() {{
                    put("3.2", DifficultyLevel.LOW);
                }};

                WrongProblemListPerPageRequest wrongProblemListPerPageRequest =
                        new WrongProblemListPerPageRequest(3, wrongProblems);

                List<WrongProblemListPerPageRequest> wrongProblemListPerPageRequests = List.of(wrongProblemListPerPageRequest);
                AssignmentSubmissionRequest request = new AssignmentSubmissionRequest(wrongProblemListPerPageRequests);
                SelfAssignment selfAssignment = selfAssignmentRepository.findById(1L).get();

                //when
                assignmentSubmissionService.updateMathProblemRecords(member, selfAssignment.getId(), request);

                //then
                var solvedNumForDays = solvedNumForDayRepository.findByTodayDateAndStudent(LocalDate.now(), (Student) member);
                Assertions.assertEquals(solvedNumForDays.getSolvedNum(), 9);

                var solvedNumForWeek = solvedNumForWeekRepository.findByStartOfWeekAndStudent(
                        LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)), (Student) member);
                Assertions.assertEquals(solvedNumForWeek.getSolvedNum(), 9);

                var solvedNumForMonth = solvedNumForMonthRepository.findByMonthDateAndStudent(LocalDate.now().withDayOfMonth(1), (Student) member);
                Assertions.assertEquals(solvedNumForMonth.getSolvedNum(), 9);
            }
        }
        
    }


}
