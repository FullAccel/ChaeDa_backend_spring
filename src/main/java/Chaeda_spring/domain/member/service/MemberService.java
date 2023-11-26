package Chaeda_spring.domain.member.service;

import Chaeda_spring.domain.member.dto.MemberRequestDto;
import Chaeda_spring.domain.member.dto.StudentRequestDto;
import Chaeda_spring.domain.member.entity.Member;
import Chaeda_spring.domain.member.entity.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

public class MemberService {

    private MemberRepository memberRepository;

    @Transactional
    public void saveMember(MemberRequestDto memberRequestDto){
        Member member = memberRepository.findByEmail(memberRequestDto.getEmail())
                .orElseGet(() -> identifyStudentTeacher(memberRequestDto));
    }

    private Member identifyStudentTeacher(MemberRequestDto memberRequestDto){
        Member member = null;
        if(memberRequestDto instanceof StudentRequestDto){
            member = (Member) ((StudentRequestDto) memberRequestDto).toEntity();
        }

        return member;
    }
}
