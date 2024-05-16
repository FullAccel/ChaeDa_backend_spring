package Chaeda_spring.domain.submission.assignment.service;

import Chaeda_spring.DatabaseCleaner;
import Chaeda_spring.domain.Problem.math.MathProblemType;
import Chaeda_spring.domain.Problem.math.MathProblemTypeRepository;
import Chaeda_spring.domain.member.entity.MemberRepository;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.statistics.entity.problem_type_statistics.SubconceptStatisticsForMonth;
import Chaeda_spring.domain.statistics.entity.problem_type_statistics.SubconceptStatisticsForMonthRepository;
import Chaeda_spring.domain.statistics.entity.problem_type_statistics.SubconceptStatisticsForWeek;
import Chaeda_spring.domain.statistics.entity.problem_type_statistics.SubconceptStatisticsForWeekRepository;
import Chaeda_spring.domain.textbook.entity.TextbookRespository;
import Chaeda_spring.global.constant.*;
import Chaeda_spring.global.utils.MemberUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;

import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class SubconceptStatisticsUpdaterTest {

    @Autowired
    SubconceptStatisticsForWeekRepository subconceptStatisticsForWeekRepository;
    @Autowired
    SubconceptStatisticsForMonthRepository subconceptStatisticsForMonthRepository;
    @Autowired
    SubconceptStatisticsUpdater statisticsUpdater;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TextbookRespository textbookRespository;
    Student student;
    MathProblemType mathProblemType;
    @MockBean
    MemberUtils memberUtils;
    @Autowired
    MathProblemTypeRepository mathProblemTypeRepository;
    @Autowired
    private DatabaseCleaner databaseCleaner;

    @BeforeEach
    public void setUp() {
        databaseCleaner.execute();
        student = Student.builder()
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
        
        mathProblemType = MathProblemType.builder()
                .subject(Subject.Calculus)
                .chapter(Chapter.Differential_Calculus)
                .subConcept(SubConcept.Applications_of_Definite_Integrals_in_Calculus)
                .build();
        mathProblemTypeRepository.save(mathProblemType);
    }

    @Test
    public void 세부개념_통계_정상_업데이트() {
        // given
        when(memberUtils.getCurrentMember()).thenReturn(student);
        HashMap<String, DifficultyLevel> wrongProblemRecordMap = new HashMap<>();
        wrongProblemRecordMap.put("1", DifficultyLevel.HIGH);

        // when
        statisticsUpdater.updateSubconceptStatistics(mathProblemType, true, "1", wrongProblemRecordMap);


        // then
        SubconceptStatisticsForWeek week = subconceptStatisticsForWeekRepository
                .findByStartOfWeekAndStudentAndType(
                        LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)),
                        student,
                        mathProblemType
                );
        Assertions.assertEquals(week.getHardNum(), 1);
        Assertions.assertEquals(week.getEasyNum(), 0);
        Assertions.assertEquals(week.getMiddleNum(), 0);
        Assertions.assertEquals(week.getSolvedNum(), 1);
        Assertions.assertEquals(week.getWrongNum(), 1);


        SubconceptStatisticsForMonth month = subconceptStatisticsForMonthRepository
                .findByTargetMonthAndStudentAndType(
                        LocalDate.now().withDayOfMonth(1),
                        student,
                        mathProblemType
                );
        Assertions.assertEquals(month.getHardNum(), 1);
        Assertions.assertEquals(month.getEasyNum(), 0);
        Assertions.assertEquals(month.getMiddleNum(), 0);
        Assertions.assertEquals(month.getSolvedNum(), 1);
        Assertions.assertEquals(month.getWrongNum(), 1);


    }

}
