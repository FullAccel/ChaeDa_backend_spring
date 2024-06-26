package Chaeda_spring.domain.member.controller;

import Chaeda_spring.domain.File.dto.UploadImageCompleteRequest;
import Chaeda_spring.domain.member.dto.*;
import Chaeda_spring.domain.member.entity.Member;
import Chaeda_spring.domain.member.service.MemberService;
import Chaeda_spring.global.annotation.AuthUser;
import Chaeda_spring.global.security.jwt.dto.TokenDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/student")
    @Operation(summary = "학생 정보 가져오기")
    public ResponseEntity<StudentResponse> getStudentInfo(
    ) {
        return ResponseEntity.ok(memberService.getStudentInfo());
    }

    @GetMapping("/teacher")
    @Operation(summary = "선생님 정보 가져오기")
    public ResponseEntity<TeacherResponse> getTeacherInfo(
    ) {
        return ResponseEntity.ok(memberService.getTeacherInfo());
    }

    @PostMapping("/login")
    @Operation(summary = "로그인하기")
    public ResponseEntity<TokenDto> login(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(memberService.login(request));
    }

    @PostMapping("/logout")
    @Operation(summary = "로그아웃하기")
    public ResponseEntity<Void> logout(
            @AuthUser Member member
    ) {
        memberService.logout(member);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping("/student/signUp")
    @Operation(summary = "회원가입하기")
    public ResponseEntity<Void> signUpForStudent(@RequestBody SignUpRequestForStudent request) {
        memberService.signUp(request);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping("/teacher/signUp")
    @Operation(summary = "회원가입하기")
    public ResponseEntity<Void> signUpForTeacher(@RequestBody SignUpRequestForTeacher request) {
        memberService.signUp(request);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping("/profile-image/{memberId}")
    @Operation(summary = "회원 프로필 이미지 수정하기", description = "1~6번 회원의 프로필을 수정하지 말아주세요. (= 더미 데이터는 조회만 하세요)")

    public ResponseEntity<Void> updateMemberProfileImage(
            @AuthUser Member member,
            @RequestBody UploadImageCompleteRequest request
    ) {
        memberService.updateMemberProfileImage(member, request);
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("/profile-image/{memberId}")
    @Operation(summary = "회원 프로필 이미지 삭제하기", description = "1~6번 회원의 프로필을 삭제하지 말아주세요. (= 더미 데이터는 조회만 하세요)")
    public ResponseEntity<Void> deleteMemberProfileImage(
            @AuthUser Member member
    ) {
        memberService.deleteMemberProfileImage(member);
        return ResponseEntity.ok().body(null);
    }

}

