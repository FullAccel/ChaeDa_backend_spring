package Chaeda_spring.domain.submission.service;

import Chaeda_spring.domain.announcement.dto.HwAnnouncementSimpleResponseDto;
import Chaeda_spring.domain.member.entity.MemberRepository;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.submission.dto.SubmissionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final MemberRepository memberRepository;

    public List<SubmissionResponseDto> getHwToStudent(Long memberId, LocalDate date) {
        Student student = (Student) memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("해당 Id의 멤버가 존재하지 않습니다."));

        return student.getSubmissionList().stream()
                .map(SubmissionResponseDto::new)
                .filter(res -> res.getCreatedTime().toLocalDate().isEqual(date))
                .collect(Collectors.toList());
    }
}
