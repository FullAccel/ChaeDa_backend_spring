package Chaeda_spring.domain.member.service;

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
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND, "id : " + memberId + ", 해당 Id의 멤버가 존재하지 않습니다."));

        String presignedUrl = imageService.getPresignedUrlByImage(member.getImage());
        if (member instanceof Student) {
            return StudentResponse.from((Student) member, presignedUrl);
        }
        return TeacherResponse.from((Teacher) member, presignedUrl);
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
            teacher = (Teacher) memberRepository.findById(memberId)
                    .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND, "id : " + memberId + ", 해당 Id의 멤버가 존재하지 않습니다."));
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
            student = (Student) memberRepository.findById(memberId)
                    .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND, "id : " + memberId + ", 해당 Id의 멤버가 존재하지 않습니다."));
        } catch (ClassCastException e) {
            throw new NotEqualsException(ErrorCode.IS_NOT_STUDENT, "id : " + memberId + ", 해당 멤버는 학생 회원이 아닙니다.");
        }
        return student;
    }
}
