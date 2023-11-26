package Chaeda_spring.domain.notification.service;

import Chaeda_spring.domain.class_group.entity.ClassGroup;
import Chaeda_spring.domain.class_group.entity.ClassGroupRepository;
import Chaeda_spring.domain.class_group.service.ClassGroupService;
import Chaeda_spring.domain.member.entity.MemberRepository;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.member.entity.Teacher;
import Chaeda_spring.domain.notification.dto.HwNotificationRequestDto;
import Chaeda_spring.domain.notification.dto.HwNotificationSimpleResponseDto;
import Chaeda_spring.domain.notification.entity.HomeworkNotification;
import Chaeda_spring.domain.notification.entity.HwNotificationRepository;
import Chaeda_spring.domain.textbook.entity.Textbook;
import Chaeda_spring.domain.textbook.entity.TextbookRespository;
import Chaeda_spring.global.exception.NotEqualsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HwNotificationService {

    private final HwNotificationRepository hwNotificationRepository;
    private final ClassGroupRepository classGroupRepository;
    private final MemberRepository memberRepository;
    private final ClassGroupService classGroupService;
    private final TextbookRespository textbookRespository;

    @Transactional
    public Long uploadHomeworkNotification(Long classId, Long memberId, HwNotificationRequestDto dto) {

        ClassGroup classGroup = classGroupRepository.findById(classId)
                .orElseThrow(() -> new NotFoundException("해당 Id의 클래스가 존재하지 않습니다."));

        Teacher teacher = (Teacher) memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("해당 Id의 멤버가 존재하지 않습니다."));

        if(teacher.getId() != classGroup.getId()){
            throw new NotEqualsException("해당 클래스 담당 선생님만 숙제를 공지할 수 있습니다");
        }

        Textbook textbook = textbookRespository.findById(dto.getTextBookId())
                .orElseThrow(() -> new NotFoundException("해당 Id의 교재가 존재하지 않습니다."));

        HomeworkNotification hwNotification = dto.toEntity();
        hwNotification.setTextbookImageUrl(textbook.getImageUrl());
        //선생님에 숙제공지 연결
        hwNotification.setTargetClassGroup(classGroup);
        hwNotification.setTeacher(teacher);
        //클래스 소속 학생들이게 숙제공지 연결
        classGroupService.connectHomeworkToStudent(classGroup, hwNotification);

        return hwNotificationRepository.save(hwNotification).getId();
    }

    public List<HwNotificationSimpleResponseDto> getHwToTeacher(Long memberId) {
        Teacher teacher = (Teacher) memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("해당 Id의 멤버가 존재하지 않습니다."));

        return teacher.getHomeworkNotificationList().stream()
                .map(HwNotificationSimpleResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<HwNotificationSimpleResponseDto> getHwToStudent(Long memberId, LocalDate date) {
        Student student = (Student) memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("해당 Id의 멤버가 존재하지 않습니다."));

        return student.getSubmissionList().stream()
                .map(submission -> new HwNotificationSimpleResponseDto(submission.getHomeworkNotification()))
                .filter(res -> res.getDeadLine().toLocalDate().isEqual(date))
                .collect(Collectors.toList());
    }
}
