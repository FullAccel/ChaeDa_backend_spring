package Chaeda_spring.domain.member.service;

import Chaeda_spring.domain.image.dto.UploadImageCompleteRequest;
import Chaeda_spring.domain.image.entity.Image;
import Chaeda_spring.domain.image.service.ImageService;
import Chaeda_spring.domain.member.dto.LoginRequest;
import Chaeda_spring.domain.member.dto.SignUpRequest;
import Chaeda_spring.domain.member.dto.StudentResponse;
import Chaeda_spring.domain.member.dto.TeacherResponse;
import Chaeda_spring.domain.member.entity.*;
import Chaeda_spring.global.exception.AlreadyExistException;
import Chaeda_spring.global.exception.ErrorCode;
import Chaeda_spring.global.exception.NotEqualsException;
import Chaeda_spring.global.exception.NotFoundException;
import Chaeda_spring.global.security.jwt.dto.TokenDto;
import Chaeda_spring.global.security.jwt.entity.RefreshTokenRepository;
import Chaeda_spring.global.security.jwt.service.JwtTokenService;
import Chaeda_spring.global.utils.MemberUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberUtils memberUtils;
    private final MemberRepository memberRepository;
    private final ImageService imageService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService tokenService;
    private final RefreshTokenRepository refreshTokenRepository;


    /**
     * 아이디 비밀번호를 받아 인증을 진행하고 성공시 토큰을 발급합니다.
     *
     * @param request
     * @return {@link TokenDto} AT와 RT가 담긴 Dto를 전달합니다.
     */
    public TokenDto login(LoginRequest request) {
        Member member = memberRepository.findMemberByLoginId(request.loginId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.LOGIN_ID_NOT_FOUND));

        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new NotEqualsException(ErrorCode.PASSWORD_NOT_MATCHES);
        }
        if (refreshTokenRepository.existsRefreshTokenByMemberId(member.getId())) {
            log.info("기존의 존재하는 refresh 토큰 삭제");
            refreshTokenRepository.deleteById(member.getId());
        }
        return tokenService.createTokenDto(member.getId(), member.getRole());
    }

    public void logout(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.LOGIN_ID_NOT_FOUND));

        if (refreshTokenRepository.existsRefreshTokenByMemberId(member.getId())) {
            log.info("기존의 존재하는 refresh 토큰 삭제");
            refreshTokenRepository.deleteById(member.getId());
        }
    }


    /**
     * 회원 정보를 받고 Role에 따라 학생과 선생님 회원을 구분하여 저장합니다.
     *
     * @param {@link SignUpRequest}
     */
    @Transactional
    public void signUp(SignUpRequest request) {
        if (memberRepository.existsMemberByEmail(request.email())) {
            throw new AlreadyExistException(ErrorCode.ALREADY_EXIST_MEMBER);
        }

        memberRepository.save(Member.createSignUpMember(request, passwordEncoder.encode(request.password())));
    }

    /**
     * 현재 회원의 학생 정보를 검색합니다.
     *
     * @return 학생 정보가 포함된 응답입니다.
     * @throws NotEqualsException 현재 회원이 학생이 아닌 경우.
     */
    public StudentResponse getStudentInfo() {
        Member member = memberUtils.getCurrentMember();

        String presignedUrl = (member.getImage() != null) ? imageService.getPresignedUrlByImage(member.getImage()) : null;

        if (!member.getRole().equals(Role.STUDENT)) {
            throw new NotEqualsException(ErrorCode.IS_NOT_STUDENT);
        }
        return StudentResponse.from((Student) member, presignedUrl);

    }

    /**
     * 현재 로그인한 교사에 대한 정보를 검색합니다.
     *
     * @return 교사 정보 및 가능한 경우 프로필 이미지의 URL이 포함된 {@link TeacherResponse}입니다.
     * @throws NotEqualsException 로그인한 회원이 교사가 아닌 경우.
     */
    public TeacherResponse getTeacherInfo() {
        Member member = memberUtils.getCurrentMember();

        String presignedUrl = (member.getImage() != null) ? imageService.getPresignedUrlByImage(member.getImage()) : null;

        if (!member.getRole().equals(Role.TEACHER)) {
            throw new NotEqualsException(ErrorCode.IS_NOT_TEACHER);
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
