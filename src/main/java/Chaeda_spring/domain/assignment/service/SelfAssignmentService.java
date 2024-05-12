package Chaeda_spring.domain.assignment.service;

import Chaeda_spring.domain.assignment.dto.SelfAssignmentRequest;
import Chaeda_spring.domain.assignment.dto.SelfAssignmentResponse;
import Chaeda_spring.domain.assignment.entity.SelfAssignment;
import Chaeda_spring.domain.assignment.entity.SelfAssignmentRepository;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.textbook.entity.Textbook;
import Chaeda_spring.domain.textbook.entity.TextbookRespository;
import Chaeda_spring.global.exception.ErrorCode;
import Chaeda_spring.global.exception.NotFoundException;
import Chaeda_spring.global.utils.MemberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SelfAssignmentService {

    private final SelfAssignmentRepository selfAssignmentRepository;
    private final TextbookRespository textbookRespository;
    private final MemberUtils memberUtils;

    //TODO: 과제 생성하기
    public SelfAssignmentResponse createSelfAssignment(SelfAssignmentRequest request) {
        Student student = (Student) memberUtils.getCurrentMember();
        Textbook textbook = textbookRespository.findById(request.textbookId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.TEXTBOOK_NOT_FOUND));

        SelfAssignment save = selfAssignmentRepository.save(SelfAssignment.createSelfAssignment(request, textbook, student));
        return SelfAssignmentResponse.of(save);
    }

    /**
     * Updates a self assignment based on the provided ID and request.
     *
     * @param id      The ID of the self assignment to update.
     * @param request The self assignment request containing the updated information.
     * @return The updated self assignment response.
     * @throws NotFoundException If the self assignment or textbook is not found.
     */
    //TODO: 과제 수정하기
    public SelfAssignmentResponse updateSelfAssignment(Long id, SelfAssignmentRequest request) {

        SelfAssignment selfAssignment = selfAssignmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorCode.SELF_ASSIGNMENT_NOT_FOUND));

        Textbook textbook = textbookRespository.findById(request.textbookId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.TEXTBOOK_NOT_FOUND));

        selfAssignment.updateSelfAssignment(request, textbook);
        return SelfAssignmentResponse.of(selfAssignmentRepository.save(selfAssignment));
    }

    //TODO: 과제 삭제하기
    public void deleteSelfAssignment(Long id) {
        selfAssignmentRepository.deleteById(id);
    }

    //TODO: 과제 특정 Id로 조회하기
    public SelfAssignmentResponse getSelfAssignmentById(Long id) {
        SelfAssignment selfAssignment = selfAssignmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorCode.SELF_ASSIGNMENT_NOT_FOUND));
        return SelfAssignmentResponse.of(selfAssignment);
    }

    //TODO: 과제 날짜로 한 번에 조회하기
    public List<SelfAssignmentResponse> getSelfAssignmentsByDate(LocalDate date) {
        Student student = (Student) memberUtils.getCurrentMember();

        return selfAssignmentRepository.findAllByTargetDateAndStudent(date, student).stream()
                .map(selfAssignment -> SelfAssignmentResponse.of(selfAssignment))
                .collect(Collectors.toList());
    }
}
