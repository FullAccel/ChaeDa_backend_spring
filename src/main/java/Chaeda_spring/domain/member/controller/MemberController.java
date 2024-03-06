package Chaeda_spring.domain.member.controller;

import Chaeda_spring.domain.member.dto.MemberResponse;
import Chaeda_spring.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

