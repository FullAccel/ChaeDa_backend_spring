package Chaeda_spring.domain.member.controller;

import Chaeda_spring.domain.image.dto.UploadImageCompleteRequest;
import Chaeda_spring.domain.member.dto.MemberResponse;
import Chaeda_spring.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{memberId}")
    @Operation(summary = "회원 정보 가져오기")
    public ResponseEntity<MemberResponse> getMemberInfo(
            @PathVariable Long memberId
    ) {
        return ResponseEntity.ok(memberService.getMemberInfo(memberId));
    }

    @PutMapping("/profile-image/{memberId}")
    @Operation(summary = "회원 프로필 이미지 수정하기", description = "1~6번 회원의 프로필을 수정하지 말아주세요. (= 더미 데이터는 조회만 하세요)")
    public ResponseEntity<Void> updateMemberProfileImage(
            @PathVariable Long memberId,
            @RequestBody UploadImageCompleteRequest request
    ) {
        memberService.updateMemberProfileImage(memberId, request);
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("/profile-image/{memberId}")
    @Operation(summary = "회원 프로필 이미지 삭제하기", description = "1~6번 회원의 프로필을 삭제하지 말아주세요. (= 더미 데이터는 조회만 하세요)")
    public ResponseEntity<Void> deleteMemberProfileImage(
            @PathVariable Long memberId
    ) {
        memberService.deleteMemberProfileImage(memberId);
        return ResponseEntity.ok().body(null);
    }

}

