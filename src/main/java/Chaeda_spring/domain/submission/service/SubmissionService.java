package Chaeda_spring.domain.submission.service;

import Chaeda_spring.domain.member.entity.MemberRepository;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.submission.dto.SubmissionImageRequestDto;
import Chaeda_spring.domain.submission.dto.SubmissionResponseDto;
import Chaeda_spring.domain.submission.entity.*;
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
    private final SubmissionRepository submissionRepository;
    private final SubmissionImageRepository submissionImageRepository;
    private final SlicingImageRepository slicingImageRepository;
    private final int ONE_IMAGE_PLUS_SLICING_NUM = 7;

    public List<SubmissionResponseDto> getHwToStudent(Long memberId, LocalDate date) {
        Student student = (Student) memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("해당 Id의 멤버가 존재하지 않습니다."));

        return student.getSubmissionList().stream()
                .map(SubmissionResponseDto::new)
                .filter(res -> res.getCreatedTime().toLocalDate().isEqual(date))
                .collect(Collectors.toList());
    }

    public void storeS3Url(Long memberId, Long homeworkId, SubmissionImageRequestDto dto) {
        List<String> urlList = dto.getImage_urls();
        for (int i = 0; i < urlList.size(); i+=ONE_IMAGE_PLUS_SLICING_NUM) {
            String beforeSlicingUrl = urlList.get(i);
            Submission submission = submissionRepository.findByHomeworkNotificationIdAndStudentId(homeworkId, memberId);

            SubmissionImage beforeSlicing = SubmissionImage.builder()
                    .imageUrl(beforeSlicingUrl)
                    .build();
            SubmissionImage submissionImage = submissionImageRepository.save(beforeSlicing);
            submissionImage.setSubmission(submission);
            for (int j = 0; j < ONE_IMAGE_PLUS_SLICING_NUM - 1; j++) {
                SlicingImage slicingImage = SlicingImage.builder()
                        .imageUrl(urlList.get(j))
                        .build();
                slicingImage = slicingImageRepository.save(slicingImage);
                slicingImage.setSubmission(submission);
            }
        }
    }
}
