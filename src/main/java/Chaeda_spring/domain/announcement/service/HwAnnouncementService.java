package Chaeda_spring.domain.announcement.service;

import Chaeda_spring.domain.announcement.dto.HwAnnouncementContentDto;
import Chaeda_spring.domain.announcement.dto.HwAnnouncementRequest;
import Chaeda_spring.domain.announcement.dto.HwAnnouncementResponseDto;
import Chaeda_spring.domain.announcement.entity.HomeworkAnnouncement;
import Chaeda_spring.domain.announcement.entity.HwAnnouncementRepository;
import Chaeda_spring.domain.class_group.entity.ClassGroup;
import Chaeda_spring.domain.class_group.entity.ClassGroupRepository;
import Chaeda_spring.domain.class_group.service.ClassGroupService;
import Chaeda_spring.domain.member.entity.MemberRepository;
import Chaeda_spring.domain.member.entity.Teacher;
import Chaeda_spring.domain.textbook.entity.Textbook;
import Chaeda_spring.domain.textbook.entity.TextbookRespository;
import Chaeda_spring.global.exception.ErrorCode;
import Chaeda_spring.global.exception.NotEqualsException;
import Chaeda_spring.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HwAnnouncementService {

    private final HwAnnouncementRepository hwAnnouncementRepository;
    private final ClassGroupRepository classGroupRepository;
    private final MemberRepository memberRepository;
    private final ClassGroupService classGroupService;
    private final TextbookRespository textbookRespository;

    @Transactional
    public Long uploadHomeworkNotification(Long classId, Long teacherId, HwAnnouncementRequest request) {

        ClassGroup classGroup = classGroupRepository.findById(classId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.CLASS_NOT_FOUND, "해당 Id의 클래스가 존재하지 않습니다."));

        Teacher teacher = (Teacher) memberRepository.findById(teacherId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND, "해당 Id의 멤버가 존재하지 않습니다."));

        if (teacher.getId() != classGroup.getId()) {
            throw new NotEqualsException(ErrorCode.MEMBER_NOT_AUTHORIZED_TO_ANNOUNCE);
        }

        Textbook textbook = textbookRespository.findById(request.textBookId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.TEXTBOOK_NOT_FOUND, "해당 Id의 교재가 존재하지 않습니다."));

        HomeworkAnnouncement hwAnnouncement = HomeworkAnnouncement.from(request, textbook, teacher, classGroup);
        classGroupService.connectHomeworkToStudent(classGroup, hwAnnouncement);

        return hwAnnouncementRepository.save(hwAnnouncement).getId();
    }

    public List<HwAnnouncementResponseDto> getHwToTeacher(Long memberId) {
        Teacher teacher = (Teacher) memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND, "해당 Id의 멤버가 존재하지 않습니다."));

        return teacher.getHomeworkNotificationList().stream()
                .map(HwAnnouncementResponseDto::new)
                .collect(Collectors.toList());
    }

    public HwAnnouncementContentDto getHwContent(Long hwAnnouncementId) {
        HomeworkAnnouncement hwAnnouncement = hwAnnouncementRepository.findById(hwAnnouncementId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND, "해당 Id의 멤버가 존재하지 않습니다."));

        return new HwAnnouncementContentDto(hwAnnouncement);
    }
}
