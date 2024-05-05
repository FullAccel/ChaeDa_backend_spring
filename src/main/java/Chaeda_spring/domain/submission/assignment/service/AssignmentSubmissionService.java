package Chaeda_spring.domain.submission.assignment.service;

import Chaeda_spring.domain.Problem.math.MathProblem;
import Chaeda_spring.domain.Problem.math.MathProblemRepository;
import Chaeda_spring.domain.assignment.entity.SelfAssignment;
import Chaeda_spring.domain.assignment.entity.SelfAssignmentRepository;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.statistics.entity.SolvedNumForDay;
import Chaeda_spring.domain.statistics.entity.SolvedNumForDayRepository;
import Chaeda_spring.domain.statistics.entity.SolvedNumForMonthRepository;
import Chaeda_spring.domain.statistics.entity.SolvedNumForWeekRepository;
import Chaeda_spring.domain.submission.assignment.dto.AssignmentSubmissionRequest;
import Chaeda_spring.domain.submission.assignment.dto.ProblemNumScopeResponse;
import Chaeda_spring.domain.submission.assignment.dto.WrongProblemWithinPageRequest;
import Chaeda_spring.domain.submission.assignment.entity.WrongProblemRecord;
import Chaeda_spring.domain.submission.assignment.entity.WrongProblemRecordRepository;
import Chaeda_spring.domain.textbook.entity.Textbook;
import Chaeda_spring.global.constant.DifficultyLevel;
import Chaeda_spring.global.exception.ErrorCode;
import Chaeda_spring.global.exception.NotFoundException;
import Chaeda_spring.global.utils.MemberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentSubmissionService {

    private final SelfAssignmentRepository selfAssignmentRepository;
    private final MathProblemRepository mathProblemRepository;
    private final WrongProblemRecordRepository wrongProblemRecordRepository;
    private final MemberUtils memberUtils;
    private final SolvedNumForDayRepository solvedNumForDayRepository;
    private final SolvedNumForWeekRepository solvedNumForWeekRepository;
    private final SolvedNumForMonthRepository solvedNumForMonthRepository;


    //TODO: 틀린 문제를 받아서 오답여부를 DB에 등록합니다. ok
    // -1 : 문제 데이터의 풀이 횟수, 틀린 횟수를 업데이트 합니다. ok
    // -2 : 해당 날짜의 푼 문제 수를 업데이트 합니다.
    // -3 : 매일 자정 이번주 푼 문제 수를 업데이트하는 스케줄러를 만듭니다.
    // -4 : 매주 자정 이번달 푼 문제 수를 업데이트하는 스케주럴를 만듭니다.
    // -5 : 세부개념별 틀린 횟수를 업데이트합니다.
    // -6 : 단원별 틀린 횟수를 업데이트합니다.

    /**
     * 과제 제출을 기반으로 수학 문제 기록을 업데이트합니다.
     *
     * @param assignmentId                과제의 ID입니다.
     * @param assignmentSubmissionRequest 과제 제출 request입니다.
     */
    @Transactional
    public void updateMathProblemRecords(Long assignmentId, AssignmentSubmissionRequest assignmentSubmissionRequest) {

        Student student = (Student) memberUtils.getCurrentMember();

        SelfAssignment selfAssignment = selfAssignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.SELF_ASSIGNMENT_NOT_FOUND));

        Textbook textbook = selfAssignment.getTextbook();

        for (WrongProblemWithinPageRequest wrongProblemWithinPageRequest : assignmentSubmissionRequest.wrongProblemWithinPageRequests()) {
            int pageNum = wrongProblemWithinPageRequest.pageNumber();
            HashMap<String, DifficultyLevel> wrongProblemRecordMap = wrongProblemWithinPageRequest.wrongProblemRecords();
            //해당 페이지에 속하는 문제들을 모두 가져옵니다.
            List<MathProblem> mathProblems = mathProblemRepository.findAllByTextbookAndPageNumber(textbook, pageNum);

            //TODO: 해당 날짜의 푼 문제 수를 업데이트를 해야합니다
            SolvedNumForDay solvedNumForDay = solvedNumForDayRepository.findByTodayDate(LocalDate.now());
            if (solvedNumForDay != null) {
                // 일간 풀이 횟수 통계가 있다면 업데이트합니다.
                solvedNumForDay.increaseSolvedNum(mathProblems.size());
                solvedNumForDayRepository.save(solvedNumForDay);
            } else {
                // 일간 풀이 횟수 통계가 없다면 생성합니다.
                SolvedNumForDay newDayStatistics = SolvedNumForDay.builder()
                        .todayDate(LocalDate.now())
                        .build();
                newDayStatistics.increaseSolvedNum(mathProblems.size());
                solvedNumForDayRepository.save(newDayStatistics);
            }

            for (MathProblem mathProblem : mathProblems) {
                //페이지에 속하는 모든 문제는 풀이 횟수가 +1됩니다.
                mathProblem.increaseSolvedStudentsCount();
                String problemNum = mathProblem.getProblemNumber();

                //페이지 속 문제 중 틀린 문제라면
                if (wrongProblemRecordMap.containsKey(problemNum)) {

                    // -- [전체] 문제의 틀린 학생 수와 난이도에 맞는 틀린 학생 수를 +1합니다.
                    //틀린 횟수를 +1 합니다.
                    mathProblem.increaseIncorrectStudentsCount();
                    //체감 난이도에 맞는 틀린 횟수를 +1 합니다.
                    DifficultyLevel difficultyLevel = wrongProblemRecordMap.get(problemNum);
                    mathProblem.increaseCountByDifficulty(difficultyLevel);

                    //틀린문제기록을 DB에 저장합니다.
                    wrongProblemRecordRepository.save(
                            WrongProblemRecord.builder()
                                    .wrongDate(selfAssignment.getTargetDate()) //과제에 등록된 날짜로 틀린 날짜가 입력됩니다.
                                    .difficulty(difficultyLevel)
                                    .mathProblem(mathProblem)
                                    .student(student)
                                    .build()
                    );

                    //TODO: 이번주 푼 문제 수를 업데이트 합니다.

                    //TODO: 이번달 푼 문제 수를 업데이트 합니다.
                }
            }
        }
    }

    public List<ProblemNumScopeResponse> getProblemNumScopes(Long assignmentId) {
        SelfAssignment selfAssignment = selfAssignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.SELF_ASSIGNMENT_NOT_FOUND));
        List<ProblemNumScopeResponse> problemNumScopes = new ArrayList<>();

        for (int page = selfAssignment.getStartPage(); page <= selfAssignment.getEndPage(); page++) {
            List<MathProblem> mathProblems = mathProblemRepository.findAllByTextbookAndPageNumber(selfAssignment.getTextbook(), page);

            List<String> problemNumbers = new ArrayList<>();
            for (MathProblem mathProblem : mathProblems) {
                problemNumbers.add(mathProblem.getProblemNumber());
            }
            problemNumScopes.add(ProblemNumScopeResponse.of(page, problemNumbers));
        }
        return problemNumScopes;
    }

}
