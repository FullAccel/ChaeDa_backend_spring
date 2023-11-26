package Chaeda_spring.domain.class_group.service;

import Chaeda_spring.domain.class_group.dto.ClassGroupResponseDto;
import Chaeda_spring.domain.class_group.entity.ClassGroup;
import Chaeda_spring.domain.member.entity.MemberRepository;
import Chaeda_spring.domain.member.entity.Teacher;
import Chaeda_spring.domain.announcement.entity.HomeworkAnnouncement;
import Chaeda_spring.domain.submission.entity.Submission;
import Chaeda_spring.domain.submission.entity.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassGroupService {

    private final MemberRepository memberRepository;
    private final SubmissionRepository submissionRepository;

    public List<ClassGroupResponseDto> getClassGroupList(Long memberId) {
        Teacher teacher = (Teacher)memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("해당 Id의 선생님이 존재하지 않습니다."));

        return teacher.getClassGroupList().stream()
                .map(ClassGroupResponseDto::new)
                .collect(Collectors.toList());
    }

    public void connectHomeworkToStudent(ClassGroup classGroup, HomeworkAnnouncement hwNotification){
        classGroup.getCourseList().stream()
                .forEach(course->{
                    Submission submission = new Submission();
                    submission.setStudent(course.getStudent());
                    submission.setHomeworkNotification(hwNotification);
                    submissionRepository.save(submission);
                });
    }
}
