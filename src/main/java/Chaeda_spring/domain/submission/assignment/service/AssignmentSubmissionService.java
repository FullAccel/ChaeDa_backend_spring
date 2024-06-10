package Chaeda_spring.domain.submission.assignment.service;

import Chaeda_spring.domain.Problem.math.MathProblem;
import Chaeda_spring.domain.Problem.math.MathProblemRepository;
import Chaeda_spring.domain.Problem.math.MathProblemType;
import Chaeda_spring.domain.Problem.math.ProblemTypeMapping;
import Chaeda_spring.domain.assignment.entity.SelfAssignment;
import Chaeda_spring.domain.assignment.entity.SelfAssignmentRepository;
import Chaeda_spring.domain.member.entity.Member;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.submission.assignment.dto.AssignmentSubmissionRequest;
import Chaeda_spring.domain.submission.assignment.dto.ProblemNumScopeResponse;
import Chaeda_spring.domain.submission.assignment.dto.WrongProblemListPerPageRequest;
import Chaeda_spring.domain.submission.assignment.entity.WrongProblemRecord;
import Chaeda_spring.domain.submission.assignment.entity.WrongProblemRecordRepository;
import Chaeda_spring.domain.textbook.entity.Textbook;
import Chaeda_spring.global.constant.DifficultyLevel;
import Chaeda_spring.global.exception.ErrorCode;
import Chaeda_spring.global.exception.NotEqualsException;
import Chaeda_spring.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentSubmissionService {

    private final SelfAssignmentRepository selfAssignmentRepository;
    private final MathProblemRepository mathProblemRepository;
    private final WrongProblemRecordRepository wrongProblemRecordRepository;

    private final SubconceptStatisticsUpdater subconceptStatisticsUpdater;
    private final AccumulatedStatisticsUpdater accumulatedStatisticsUpdater;
    private final SolvedNumStatisticsUpdater solvedNumStatisticsUpdater;


    //TODO: 틀린 문제를 받아서 오답여부를 DB에 등록합니다. ok
    // -1 : 문제 데이터의 풀이 횟수, 틀린 횟수를 업데이트 합니다. ok
    // -2 : 해당 날짜의 푼 문제 수를 업데이트 합니다.
    // -3 : 세부개념별 틀린 횟수를 업데이트합니다.
    // -4 : 단원별 틀린 횟수를 업데이트합니다.
    // -5 : 매일 자정 이번주 푼 문제 수를 업데이트하는 스케줄러를 만듭니다.
    // -6 : 매주 자정 이번달 푼 문제 수를 업데이트하는 스케주럴를 만듭니다.

    @Transactional
    public void updateMathProblemRecords(Member member, Long assignmentId, AssignmentSubmissionRequest request) {

        Student student = (Student) member;
        SelfAssignment selfAssignment = selfAssignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.SELF_ASSIGNMENT_NOT_FOUND));
        if (selfAssignment.getStudent().getId() != student.getId())
            throw new NotEqualsException(ErrorCode.SELF_ASSIGNMENT_NOT_FOUND, "해당 과제는 본인의 과제가 아닙니다.");

        selfAssignment.setCompleted();
        selfAssignmentRepository.save(selfAssignment);

        //페이지 단위로 틀린 문제를 DB에 기록합니다.
        request.wrongProblemListPerPageRequests().forEach(listPerPage ->
                handleWrongProblemListPerPage(listPerPage, selfAssignment, student));
    }

    /**
     * 페이지 내에서 틀린 문제에 대해서 통계 처리를 합니다.
     *
     * @param request        페이지 번호와 틀린 문제 기록이 포함된 요청입니다.
     * @param selfAssignment The self-assignment entity.
     * @param student        The student entity.
     */
    private void handleWrongProblemListPerPage(WrongProblemListPerPageRequest request, SelfAssignment selfAssignment, Student student) {
        Textbook textbook = selfAssignment.getTextbook();
        int pageNum = request.pageNumber();
        HashMap<String, DifficultyLevel> wrongProblemRecordMap = request.wrongProblemRecords();
        List<MathProblem> mathProblems = mathProblemRepository.findAllByTextbookAndPageNumber(textbook, pageNum);

        //해당 페이지에 있는 문제 수 만큼 오늘 푼 문제 수를 업데이트 합니다.
        updateSolvedNum(mathProblems.size(), student);

        //목록에 있는 각 수학 문제에 대해서 유형을 판단하고 풀이 횟수와 틀린 횟수를 업데이트합니다
        handleMathProblems(mathProblems, wrongProblemRecordMap, selfAssignment, student);
    }

    /**
     * 현재 날짜에 풀었던 문제 수를 업데이트하거나 저장합니다.
     *
     * @param solvedNum 업데이트하거나 저장할 해결한 문제 수입니다.
     * @param student   문제 풀이 횟수를 업데이트할 target 학생입니다.
     */
    private void updateSolvedNum(int solvedNum, Student student) {
        solvedNumStatisticsUpdater.updateSolvedNumForDay(solvedNum, student);
        solvedNumStatisticsUpdater.updateSolvedNumForWeek(solvedNum, student);
        solvedNumStatisticsUpdater.updateSolvedNumForMonth(solvedNum, student);
    }


    /**
     * 목록에 있는 각 수학 문제에 대해서 유형을 판단하고 풀이 횟수와 틀린 횟수를 업데이트합니다.
     * 특히, 틀리 문제라면 체감 난이도에 대한 통계도 업데이트합니다.
     *
     * @param mathProblems          The list of math problems to handle.
     * @param wrongProblemRecordMap The map containing wrong problem records for each problem number.
     * @param selfAssignment        The self-assignment entity.
     * @param student               The student entity.
     */
    private void handleMathProblems(List<MathProblem> mathProblems, HashMap<String, DifficultyLevel> wrongProblemRecordMap, SelfAssignment selfAssignment, Student student) {
        for (MathProblem mathProblem : mathProblems) {

            //해당 문제를 사용자가 틀렸는지 판단합니다.
            String problemNum = mathProblem.getProblemNumber();
            boolean isWrong = wrongProblemRecordMap.containsKey(problemNum);

            //틀린 문제라면 WrongProblemRecord를 생성합니다.
            if (isWrong) {
                saveWrongMathProblem(mathProblem, wrongProblemRecordMap, selfAssignment, student);
            }

            //문제가 속하는 유형 타입에 맞게 통계를 업데이트 합니다.
            for (ProblemTypeMapping mapping : mathProblem.getProblemTypeMappings()) {
                MathProblemType mathProblemType = mapping.getMathProblemType();
                //주간, 월간 세부개념 통계, 단원,세부 개념 별 누적 통계 update
                processStatistics(isWrong, problemNum, wrongProblemRecordMap, student, mathProblemType);
            }
        }
    }

    /**
     * 주간, 월간 세부개념 통계를 업데이트합니다.
     *
     * @param isWrong               a boolean value indicating if the problem is wrong
     * @param problemNum            the problem number
     * @param wrongProblemRecordMap a map containing wrong problem records for each problem number
     * @param student               the student entity
     * @param mathProblemType       the math problem type
     */
    private void processStatistics(boolean isWrong, String problemNum, HashMap<String, DifficultyLevel> wrongProblemRecordMap, Student student, MathProblemType mathProblemType) {
        //세부개념 통계를 업데이트 합니다.
        subconceptStatisticsUpdater.updateSubconceptStatistics(mathProblemType, isWrong, problemNum, wrongProblemRecordMap);
        //누적 통계를 업데이트 합니다.
        accumulatedStatisticsUpdater.updateAccumulatedStatistics(mathProblemType, isWrong, problemNum, wrongProblemRecordMap);
    }

    /**
     * 주어진 수학 문제에 대해 틀린 학생의 수를 늘리고, 수학 문제의 특정 난이도로 틀린 학생 수를 업데이트하고
     * 틀린 문제의 기록을 저장소에 저장합니다.
     *
     * @param mathProblem           The MathProblem for which the wrong problem is being handled.
     * @param wrongProblemRecordMap The map containing wrong problem records for each problem number.
     * @param selfAssignment        The SelfAssignment entity.
     * @param student               The Student entity.
     */
    private void saveWrongMathProblem(MathProblem mathProblem, HashMap<String, DifficultyLevel> wrongProblemRecordMap, SelfAssignment selfAssignment, Student student) {

        DifficultyLevel difficultyLevel = wrongProblemRecordMap.get(mathProblem.getProblemNumber());

        WrongProblemRecord record = WrongProblemRecord.builder()
                .incorrectDate(selfAssignment.getTargetDate())
                .difficulty(difficultyLevel)
                .mathProblem(mathProblem)
                .student(student)
                .build();
        wrongProblemRecordRepository.save(record);
    }

    /**
     * 주어진 과제 내에서 페이지별 문제 번호의 범위를 나타내는 ProblemNumScopeResponse 객체 목록을 검색합니다.
     *
     * @param assignmentId The ID of the assignment.
     * @return 페이자 별 문제 번호 범위를 나타내는 ProblemNumScopeResponse 객체 목록입니다.
     * @throws NotFoundException If the self-assignment with the given ID is not found.
     */
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
