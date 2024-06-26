package Chaeda_spring.deprecated.announcement.service;

import Chaeda_spring.deprecated.announcement.dto.HwAnnounceSummaryResponse;
import Chaeda_spring.deprecated.announcement.dto.HwAnnouncementRequest;
import Chaeda_spring.deprecated.announcement.dto.HwAnnouncementResponse;
import Chaeda_spring.deprecated.announcement.entity.HwAnnouncement;
import Chaeda_spring.deprecated.announcement.entity.HwAnnouncementRepository;
import Chaeda_spring.deprecated.class_group.entity.ClassGroup;
import Chaeda_spring.deprecated.class_group.entity.ClassGroupRepository;
import Chaeda_spring.domain.member.entity.MemberRepository;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.member.entity.Teacher;
import Chaeda_spring.domain.submission.homework.entity.homework.SubmissionHWRepository;
import Chaeda_spring.domain.submission.homework.entity.homework.SubmissionHomework;
import Chaeda_spring.domain.submission.homework.service.ImageSubmissionService;
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
    private final ImageSubmissionService imageSubmissionService;
    private final TextbookRespository textbookRespository;
    private final SubmissionHWRepository submissionHWRepository;

    @Transactional
    public Long uploadHwAnnouncement(Long classId, Long teacherId, HwAnnouncementRequest request) {

        ClassGroup classGroup = classGroupRepository.findById(classId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.CLASS_NOT_FOUND, "해당 Id의 클래스가 존재하지 않습니다."));
        Teacher teacher;
        try {
            teacher = (Teacher) memberRepository.findById(teacherId)
                    .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND, "해당 Id의 멤버가 존재하지 않습니다."));
        } catch (ClassCastException e) {
            throw new NotEqualsException(ErrorCode.IS_NOT_TEACHER);
        }

        if (teacher.getId() != classGroup.getId()) {
            throw new NotEqualsException(ErrorCode.MEMBER_NOT_AUTHORIZED_TO_ANNOUNCE);
        }

        Textbook textbook = textbookRespository.findById(request.textBookId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.TEXTBOOK_NOT_FOUND, "해당 Id의 교재가 존재하지 않습니다."));

        HwAnnouncement hwAnnouncement = HwAnnouncement.from(request, textbook, teacher, classGroup);
        hwAnnouncementRepository.save(hwAnnouncement);
        assignHomeworkToStudentsInClassGroup(classGroup, hwAnnouncement);

        return hwAnnouncement.getId();
    }

    private void assignHomeworkToStudentsInClassGroup(ClassGroup classGroup, HwAnnouncement hwAnnouncement) {
        classGroup.getCourseList().stream()
                .forEach(course -> {
                    SubmissionHomework submissionHomework = new SubmissionHomework();
                    submissionHomework.setStudent(course.getStudent());
                    submissionHomework.setHwAnnouncement(hwAnnouncement);
                    submissionHWRepository.save(submissionHomework);
                });
    }

    public List<HwAnnouncementResponse> getHwToTeacher(Long teacherId) {
        Teacher teacher;
        try {
            teacher = (Teacher) memberRepository.findById(teacherId)
                    .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND, "해당 Id의 멤버가 존재하지 않습니다."));
        } catch (ClassCastException e) {
            throw new NotEqualsException(ErrorCode.IS_NOT_TEACHER);
        }

        return teacher.getHomeworkNotificationList().stream()
                .map(HwAnnouncementResponse::of)
                .collect(Collectors.toList());
    }

    public HwAnnouncementResponse getHwContent(Long hwAnnouncementId) {
        HwAnnouncement hwAnnouncement = hwAnnouncementRepository.findById(hwAnnouncementId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.ANNOUNCEMENT_NOT_FOUND, "해당 Id의 숙제 공지가 존재하지 않습니다."));

        return HwAnnouncementResponse.of(hwAnnouncement);
    }

    public List<HwAnnounceSummaryResponse> getHomeworkSummaryListByDate(Long studentId, LocalDate targetDate) {
        Student student;

        try {
            student = (Student) memberRepository.findById(studentId)
                    .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND, "해당 Id의 학생이 존재하지 않습니다."));
        } catch (ClassCastException e) {
            throw new NotEqualsException(ErrorCode.IS_NOT_STUDENT);
        }

        List<HwAnnounceSummaryResponse> responseList = new ArrayList<>();

        for (SubmissionHomework submissionHomework : student.getSubmissionHomeworkList()) {
            HwAnnouncement announcement = submissionHomework.getHwAnnouncement();
            if (announcement.getDeadLineDate().isEqual(targetDate))
                responseList.add(HwAnnounceSummaryResponse.of(announcement));
        }

        return responseList;
    }
}
