package Chaeda_spring.domain.member.service;

import Chaeda_spring.domain.image.dto.UploadImageCompleteRequest;
import Chaeda_spring.domain.image.entity.Image;
import Chaeda_spring.domain.image.service.ImageService;
import Chaeda_spring.domain.member.dto.MemberResponse;
import Chaeda_spring.domain.member.dto.StudentResponse;
import Chaeda_spring.domain.member.dto.TeacherResponse;
import Chaeda_spring.domain.member.entity.Member;
import Chaeda_spring.domain.member.entity.MemberRepository;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.member.entity.Teacher;
import Chaeda_spring.global.exception.ErrorCode;
import Chaeda_spring.global.exception.NotEqualsException;
import Chaeda_spring.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ImageService imageService;

    /**
     * 해당 회원이 선생님인지 학생인지 판단하여 알맞게 해당 회원의 정보를 전달합니다.
     *
     * @param memberId
     * @return {@link StudentResponse} or {@link TeacherResponse}
     */
    public MemberResponse getMemberInfo(Long memberId) {
        Member member = findMemberById(memberId);
        String presignedUrl = (member.getImage() != null) ? imageService.getPresignedUrlByImage(member.getImage()) : "No-Image";

        if (member instanceof Student) {
            return StudentResponse.from((Student) member, presignedUrl);
        }
        return TeacherResponse.from((Teacher) member, presignedUrl);
    }

    /**
     * member id로 DB에서 해당 회원 객체를 찾아옵니다.
     *
     * @param memberId
     * @return
     */
    private Member findMemberById(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND, "id : " + memberId + ", 해당 Id의 멤버가 존재하지 않습니다."));
        return member;
    }

    /**
     * 해당 id의 회원이 선생님이면 Teacher 객체를 반환합니다.
     *
     * @param memberId
     * @return {@link Teacher}
     * @throws NotEqualsException 해당 회원이 존재하지 않습니다.
     * @throws NotEqualsException 해당 회원은 선생님 회원이 아닙니다.
     */
    public Teacher getMemberByIdIfTeacher(Long memberId) {
        Teacher teacher;
        try {
            teacher = (Teacher) findMemberById(memberId);
        } catch (ClassCastException e) {
            throw new NotEqualsException(ErrorCode.IS_NOT_TEACHER, "id : " + memberId + "해당 멤버는 선생님 회원이 아닙니다.");
        }
        return teacher;
    }

    /**
     * 해당 id의 회원이 학생이면 Student 객체를 반환합니다.
     *
     * @param memberId
     * @return {@link Student}
     * @throws NotEqualsException 해당 회원이 존재하지 않습니다.
     * @throws NotEqualsException 해당 회원은 학생 회원이 아닙니다.
     */
    public Student getMemberByIdIfStudent(Long memberId) {
        Student student;
        try {
            student = (Student) findMemberById(memberId);
        } catch (ClassCastException e) {
            throw new NotEqualsException(ErrorCode.IS_NOT_STUDENT, "id : " + memberId + ", 해당 멤버는 학생 회원이 아닙니다.");
        }
        return student;
    }

    /**
     * 해당 회원의 프로필 사진을 수정합니다.
     *
     * @param memberId
     * @param request
     */
    @Transactional
    public void updateMemberProfileImage(Long memberId, UploadImageCompleteRequest request) {
        Member member = findMemberById(memberId);
        Image profile = member.getImage();
        profile.updateImageKey(request.imageKey());

        imageService.deleteImageInS3(profile);
    }

    /**
     * 해당 회원의 프로필 사진을 삭제합니다.
     *
     * @param memberId
     */
    @Transactional
    public void deleteMemberProfileImage(Long memberId) {
        Member member = findMemberById(memberId);
        Image profile = member.getImage();
        imageService.deleteImageInDB(profile);
        imageService.deleteImageInS3(profile);
    }
}
