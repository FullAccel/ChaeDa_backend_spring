package Chaeda_spring.global.utils;

import Chaeda_spring.domain.member.entity.Member;
import Chaeda_spring.domain.member.entity.MemberRepository;
import Chaeda_spring.global.exception.CustomException;
import Chaeda_spring.global.exception.ErrorCode;
import Chaeda_spring.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberUtils {

    private final MemberRepository memberRepository;

    public Member getCurrentMember() {
        return memberRepository
                .findById(getCurrentMemberId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));
    }

    public Member getMemberByMemberId(Long memberId) {
        return memberRepository
                .findById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));
    }

    private Long getCurrentMemberId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            return Long.parseLong(authentication.getName());
        } catch (Exception e) {
            throw new CustomException(ErrorCode.AUTH_NOT_FOUND);
        }
    }
}
