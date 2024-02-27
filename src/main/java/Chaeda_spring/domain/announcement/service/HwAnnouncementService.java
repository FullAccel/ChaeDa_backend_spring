package Chaeda_spring.domain.announcement.service;

import Chaeda_spring.domain.announcement.dto.HwAnnounceSummaryResponse;
import Chaeda_spring.domain.announcement.dto.HwAnnouncementContentDto;
import Chaeda_spring.domain.announcement.dto.HwAnnouncementRequest;
import Chaeda_spring.domain.announcement.dto.HwAnnouncementResponse;
import Chaeda_spring.domain.announcement.entity.HwAnnouncement;
import Chaeda_spring.domain.announcement.entity.HwAnnouncementRepository;
import Chaeda_spring.domain.class_group.entity.ClassGroup;
import Chaeda_spring.domain.class_group.entity.ClassGroupRepository;
import Chaeda_spring.domain.course.entity.Course;
import Chaeda_spring.domain.member.entity.MemberRepository;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.member.entity.Teacher;
import Chaeda_spring.domain.submission.service.SubmissionService;
import Chaeda_spring.domain.textbook.entity.Textbook;
import Chaeda_spring.domain.textbook.entity.TextbookRespository;
import Chaeda_spring.global.exception.ErrorCode;
import Chaeda_spring.global.exception.NotEqualsException;
import Chaeda_spring.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HwAnnouncementService {

    private final HwAnnouncementRepository hwAnnouncementRepository;
    private final ClassGroupRepository classGroupRepository;
    private final MemberRepository memberRepository;
    private final SubmissionService submissionService;
    private final TextbookRespository textbookRespository;

    @Transactional
    public Long uploadHwAnnouncement(Long classId, Long teacherId, HwAnnouncementRequest request) {

        ClassGroup classGroup = classGroupRepository.findById(classId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.CLASS_NOT_FOUND, "해당 Id의 클래스가 존재하지 않습니다."));

        Teacher teacher = (Teacher) memberRepository.findById(teacherId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND, "해당 Id의 멤버가 존재하지 않습니다."));

        if (teacher.getId() != classGroup.getId()) {
            throw new NotEqualsException(ErrorCode.MEMBER_NOT_AUTHORIZED_TO_ANNOUNCE);
        }

        Textbook textbook = textbookRespository.findById(request.textBookId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.TEXTBOOK_NOT_FOUND, "해당 Id의 교재가 존재하지 않습니다."));

        HwAnnouncement hwAnnouncement = HwAnnouncement.from(request, textbook, teacher, classGroup);
        hwAnnouncementRepository.save(hwAnnouncement);
        submissionService.assignHomeworkToStudentsInClassGroup(classGroup, hwAnnouncement);

        return hwAnnouncement.getId();
    }

    public List<HwAnnouncementResponse> getHwToTeacher(Long memberId) {
        Teacher teacher = (Teacher) memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND, "해당 Id의 멤버가 존재하지 않습니다."));

        return teacher.getHomeworkNotificationList().stream()
                .map(HwAnnouncementResponse::of)
                .collect(Collectors.toList());
    }

    public HwAnnouncementContentDto getHwContent(Long hwAnnouncementId) {
        HwAnnouncement hwAnnouncement = hwAnnouncementRepository.findById(hwAnnouncementId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND, "해당 Id의 멤버가 존재하지 않습니다."));

        return new HwAnnouncementContentDto(hwAnnouncement);
    }

    public List<HwAnnounceSummaryResponse> getHomeworkSummaryListByDate(Long studentId, LocalDate date) {
        Student student = (Student) memberRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND, "해당 Id의 학생이 존재하지 않습니다."));

        List<HwAnnounceSummaryResponse> responseList = new ArrayList<>();

        //학생과 classGroup은
        for (Course course : student.getCourseList()) {
            ClassGroup classGroup = course.getClassGroup();

            hwAnnouncementRepository.findAllByDeadLineDateAndClassGroup(date, classGroup)
                    .stream().map(HwAnnounceSummaryResponse::of)
                    .forEach(e -> responseList.add(e));
        }

        return responseList;
    }
}
