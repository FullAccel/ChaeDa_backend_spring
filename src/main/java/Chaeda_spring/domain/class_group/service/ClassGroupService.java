package Chaeda_spring.domain.class_group.service;

import Chaeda_spring.domain.class_group.dto.ClassGroupRequest;
import Chaeda_spring.domain.class_group.dto.ClassGroupResponse;
import Chaeda_spring.domain.class_group.dto.ClassGroupSummaryResponse;
import Chaeda_spring.domain.class_group.entity.ClassGroup;
import Chaeda_spring.domain.class_group.entity.ClassGroupRepository;
import Chaeda_spring.domain.course.entity.Course;
import Chaeda_spring.domain.course.entity.CourseRepository;
import Chaeda_spring.domain.image.dto.UploadImageCompleteRequest;
import Chaeda_spring.domain.image.entity.Image;
import Chaeda_spring.domain.image.entity.ImageRepository;
import Chaeda_spring.domain.image.service.ImageService;
import Chaeda_spring.domain.member.dto.StudentSummaryResponse;
import Chaeda_spring.domain.member.entity.MemberRepository;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.member.entity.Teacher;
import Chaeda_spring.domain.member.service.MemberService;
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
    private final MemberService memberService;
    private final ImageService imageService;
    private final ImageRepository imageRepository;
    private final CourseRepository courseRepository;
    private final ClassGroupRepository classGroupRepository;

    /**
     * 특정 선생님이 관리하는 클래스 목록을 가져옵니다.
     *
     * @param teacherId 선생님 Id
     * @return {@link ClassGroupSummaryResponse} list
     * @throws NotEqualsException 선생님 회원만 클래스 목록을 조회할 수 있습니다.
     */
    public List<ClassGroupSummaryResponse> getClassGroupList(Long teacherId) {
        Teacher teacher = memberService.getMemberByIdIfTeacher(teacherId);

        return teacher.getClassGroupList().stream()
                .map(classGroup -> {
                    Image classProfile = classGroup.getImage();
                    String presignedUrl = classProfile != null ? imageService.getPresignedUrlByImage(classProfile) : "no-image";
                    return ClassGroupSummaryResponse.from(classGroup, presignedUrl);
                })
                .collect(Collectors.toList());
    }

    /**
     * classGroup의 상세 정보를 리턴합니다.
     * 클래스 정보와 해당 클래스에 소속된 학생들의 요약 정보를 포함합니다.
     *
     * @param classGroupId
     * @return {@link ClassGroupResponse}
     * @throws NotEqualsException 해당 클래스가 존재하지 않을때 발생
     */
    public ClassGroupResponse getClassGroup(Long classGroupId) {

        ClassGroup classGroup = findClassGroupById(classGroupId);

        List<StudentSummaryResponse> collect = classGroup.getCourseList().stream()
                .map(course -> StudentSummaryResponse.of(course.getStudent()))
                .collect(Collectors.toList());

        String presignedUrl = classGroup.getImage() != null ? imageService.getPresignedUrlByImage(classGroup.getImage()) : "no-image";

        return ClassGroupResponse.from(classGroup, presignedUrl, collect);
    }

    /**
     * 클래스에 학생들을 추가합니다.
     *
     * @param classGroupId
     * @param memberIdList
     */
    @Transactional
    public void addStudentListInClass(Long classGroupId, List<Long> memberIdList) {

        ClassGroup classGroup = findClassGroupById(classGroupId);

        memberIdList.stream()
                .map(id -> (Student) memberRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND, "id : " + id + ", 해당 Id의 학생이 존재하지 않습니다."))
                ).forEach(student -> courseRepository.save(Course.builder()
                        .student(student)
                        .classGroup(classGroup)
                        .build()));
    }

    private ClassGroup findClassGroupById(Long classGroupId) {
        ClassGroup classGroup = classGroupRepository.findById(classGroupId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.CLASS_NOT_FOUND, "id : " + classGroupId + ", 해당 Id의 클래스가 존재하지 않습니다."));
        return classGroup;
    }


    /**
     * 클래스의 학생들을 삭제합니다.
     *
     * @param classGroupId
     * @param memberIdList
     */
    @Transactional
    public void deleteStudentListInClass(Long classGroupId, List<Long> memberIdList) {

        findClassGroupById(classGroupId);

        memberIdList.stream()
                .map(id -> (Student) memberRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND, "id : " + id + ", 해당 Id의 학생이 존재하지 않습니다."))
                ).forEach(student -> courseRepository.deleteByStudentIdAndClassGroupId(student.getId(), classGroupId));
    }

    /**
     * 클래스를 생성합니다. 클래스에 학생들을 추가하며 담당 선생님을 등록합니다.
     *
     * @param makerId 생성을 하는 사람의 Id입니다. 선생님 회원만 가능하지만 maker가 클래스 담당 선생님일 필요는 없습니다.
     * @param request 클래스를 생성하기위한 정보들이 들어있습니다. {@link ClassGroupRequest}
     * @return 생성한 class의 id를 Return 합니다.
     */
    @Transactional
    public Long createClassGroup(Long makerId, ClassGroupRequest request) {
        try {
            Teacher maker = (Teacher) memberRepository.findById(makerId)
                    .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND, "id : " + makerId + ", 해당 Id의 멤버가 존재하지 않습니다."));
        } catch (ClassCastException e) {
            throw new NotEqualsException(ErrorCode.IS_NOT_STUDENT, "id : " + makerId + ", 해당 멤버는 클래스를 생성할 권한이 없습니다. 선생님 회원만 클래스 생성 가능합니다.");
        }
        memberService.getMemberByIdIfTeacher(makerId);

        //클래스 이미지 DB 저장, 이미지 없으면 null 값 저장
        Image profileImage = request.imageKey() != null || !request.imageKey().equals("") ?
                imageRepository.save(Image.builder()
                        .imageKey(request.imageKey())
                        .imageFileExtension(request.imageFileExtension())
                        .imageType(request.imageType())
                        .build())
                : null;

        //클래스 담당 선생님 조회
        Teacher teacher = memberService.getMemberByIdIfTeacher(request.teacherId());

        //클래스 생성
        ClassGroup classGroup = classGroupRepository.save(ClassGroup.builder()
                .grade(request.grade())
                .name(request.name())
                .image(profileImage)
                .lessonDays(request.lessonDays())
                .teacher(teacher)
                .build());

        //학생과 클래스 연결
        request.studentIdList().stream()
                .map(id -> memberService.getMemberByIdIfStudent(id))
                .forEach(student -> courseRepository.save(Course.builder()
                        .student(student)
                        .classGroup(classGroup)
                        .build()));

        return classGroup.getId();
    }

    @Transactional
    public void updateClassGroupProfileImage(Long classGroupId, UploadImageCompleteRequest request) {
        ClassGroup classGroup = findClassGroupById(classGroupId);
        Image profile = classGroup.getImage();
        profile.updateImageKey(request.imageKey());

        imageService.deleteImageInS3(profile);
    }

    @Transactional
    public void deleteClassGroupProfileImage(Long classGroupId) {
        ClassGroup classGroup = findClassGroupById(classGroupId);
        Image profile = classGroup.getImage();
        imageService.deleteImageInDB(profile);
        imageService.deleteImageInS3(profile);
    }
}
