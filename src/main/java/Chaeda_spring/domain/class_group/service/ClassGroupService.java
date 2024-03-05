package Chaeda_spring.domain.class_group.service;

import Chaeda_spring.domain.class_group.dto.ClassGroupRequest;
import Chaeda_spring.domain.class_group.dto.ClassGroupResponse;
import Chaeda_spring.domain.class_group.entity.ClassGroup;
import Chaeda_spring.domain.class_group.entity.ClassGroupRepository;
import Chaeda_spring.domain.course.entity.Course;
import Chaeda_spring.domain.course.entity.CourseRepository;
import Chaeda_spring.domain.image.entity.Image;
import Chaeda_spring.domain.image.entity.ImageRepository;
import Chaeda_spring.domain.image.service.ImageService;
import Chaeda_spring.domain.member.entity.MemberRepository;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.member.entity.Teacher;
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
public class ClassGroupService {

    private final MemberRepository memberRepository;
    private final ImageService imageService;
    private final ImageRepository imageRepository;
    private final CourseRepository courseRepository;
    private final ClassGroupRepository classGroupRepository;

    public List<ClassGroupResponse> getClassGroupList(Long teacherId) {
        Teacher teacher;
        try {
            teacher = (Teacher) memberRepository.findById(teacherId)
                    .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND, "해당 Id의 멤버가 존재하지 않습니다."));
        } catch (ClassCastException e) {
            throw new NotEqualsException(ErrorCode.IS_NOT_STUDENT, "해당 멤버는 클래스를 생성할 권한이 없습니다. 선생님 회원만 클래스 생성 가능합니다.");
        }

        return teacher.getClassGroupList().stream()
                .map(classGroup -> {
                    Image classProfile = classGroup.getImage();
                    String presignedUrl = imageService.getPresignedUrlByImage(classProfile);
                    return ClassGroupResponse.from(classGroup, presignedUrl);
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public Long createClassGroup(Long makerId, ClassGroupRequest request) {
        try {
            Teacher maker = (Teacher) memberRepository.findById(makerId)
                    .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND, "해당 Id의 멤버가 존재하지 않습니다."));
        } catch (ClassCastException e) {
            throw new NotEqualsException(ErrorCode.IS_NOT_STUDENT, "해당 멤버는 클래스를 생성할 권한이 없습니다. 선생님 회원만 클래스 생성 가능합니다.");
        }

        //클래스 이미지 DB 저장
        Image profileImage = imageRepository.save(Image.builder()
                .imageKey(request.imageKey())
                .imageFileExtension(request.imageFileExtension())
                .imageType(request.imageType())
                .build());

        //클래스 담당 선생님 조회
        Teacher teacher = (Teacher) memberRepository.findById(makerId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND, "해당 Id의 멤버가 존재하지 않습니다."));

        //클래스 생성
        ClassGroup classGroup = classGroupRepository.save(ClassGroup.builder()
                .grade(request.grade())
                .name(request.name())
                .image(profileImage)
                .teacher(teacher)
                .build());

        //학생과 클래스 연결
        request.studentIdList().stream()
                .map(id -> (Student) memberRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND, "해당 Id의 멤버가 존재하지 않습니다."))
                ).forEach(student -> courseRepository.save(Course.builder()
                        .student(student)
                        .classGroup(classGroup)
                        .build()));

        return classGroup.getId();
    }
}
