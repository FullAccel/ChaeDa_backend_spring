package Chaeda_spring.domain.review_note.problem.service;

import Chaeda_spring.cloud_service_agents.s3.S3Utils;
import Chaeda_spring.domain.File.dto.ImageResponse;
import Chaeda_spring.domain.File.dto.UploadImageCompleteRequest;
import Chaeda_spring.domain.File.entity.Image;
import Chaeda_spring.domain.File.entity.ImageRepository;
import Chaeda_spring.domain.File.service.ImageService;
import Chaeda_spring.domain.member.entity.Member;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.review_note.problem.dto.IncorrectProblemForReviewNoteRequest;
import Chaeda_spring.domain.review_note.problem.dto.IncorrectProblemRecordRequest;
import Chaeda_spring.domain.review_note.problem.dto.ReviewNoteProblemInfo;
import Chaeda_spring.domain.review_note.problem.dto.ReviewNoteProblemResponse;
import Chaeda_spring.domain.review_note.problem.entity.ReviewNoteProblem;
import Chaeda_spring.domain.review_note.problem.entity.ReviewNoteProblemRepository;
import Chaeda_spring.domain.submission.assignment.entity.WrongProblemRecord;
import Chaeda_spring.domain.submission.assignment.entity.WrongProblemRecordRepository;
import Chaeda_spring.global.exception.ErrorCode;
import Chaeda_spring.global.exception.NotEqualsException;
import Chaeda_spring.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewNoteProblemService {

    private final ReviewNoteProblemRepository reviewNoteProblemRepository;
    private final ImageService imageService;
    private final ImageRepository imageRepository;
    private final S3Utils s3Utils;
    private final WrongProblemRecordRepository wrongProblemRecordRepository;

    /**
     * 사용자로부터 수신된 정보를 사용하여 ReviewNoteProblem을 생성하고 저장합니다.
     *
     * @param member  문제를 생성하는 학생 관련 회원 정보
     * @param request 문제 생성에 필요한 정보를 포함하는 요청 객체
     * @throws NotFoundException 해당 텍스트북을 찾지 못한 경우 예외를 발생시킵니다.
     */
    public void createReviewNoteProblem(Member member, ReviewNoteProblemInfo request) {
        reviewNoteProblemRepository.save(
                ReviewNoteProblem.of((Student) member, request)
        );
    }

    /**
     * 주어진 회원의 오답 저장소에 있는 문제 목록을 가져옵니다.
     *
     * @param member 문제를 가져올 회원.
     * @return ReviewNoteProblemResponse 객체 목록으로, 문제와 해당 이미지 URL을 포함합니다.
     * @throws IllegalArgumentException 주어진 회원이 Student 타입이 아닌 경우 발생합니다.
     */
    public List<ReviewNoteProblemResponse> getProblemsInInCorrectStorage(Member member) {
        List<ReviewNoteProblem> reviewNoteProblemList = reviewNoteProblemRepository.findAllByStudent((Student) member);
        List<UploadImageCompleteRequest> imageRequest = reviewNoteProblemList.stream()
                .map(problem -> UploadImageCompleteRequest.of(
                        problem.getImageType(),
                        problem.getFileExtension(),
                        problem.getImageKey()
                )).collect(Collectors.toList());

        List<ImageResponse> imageReadUrls = imageService.getImageReadUrl(imageRequest);

        List<ReviewNoteProblemResponse> response = new ArrayList<>();
        for (int i = 0; i < reviewNoteProblemList.size(); i++) {
            response.add(ReviewNoteProblemResponse.of(reviewNoteProblemList.get(i), imageReadUrls.get(i)));
        }
        return response;
    }

    /**
     * 주어진 회원의 저장소에서 문제를 삭제하고 s3에서도 해당 문제의 이미지를 삭제합니다.
     *
     * @param member               문제를 삭제할 회원.
     * @param reviewNoteProblemIds 삭제할 문제들의 ID 목록.
     * @throws NotFoundException  주어진 문제 ID에 해당하는 문제가 존재하지 않을 경우 발생하거나, 주어진 이미지 키에 해당하는 이미지가 존재하지 않을 경우 발생합니다.
     * @throws NotEqualsException 문제의 등록자가 회원과 다를 경우 발생합니다.
     */
    public void deleteProblemFromStorage(Member member, List<Long> reviewNoteProblemIds) {
        reviewNoteProblemIds.stream()
                .forEach(id -> {

                    ReviewNoteProblem problem = reviewNoteProblemRepository.findById(id)
                            .orElseThrow(() -> new NotFoundException(ErrorCode.PROBLEM_NOT_FOUND));

                    if (!problem.getStudent().equals(member)) {
                        throw new NotEqualsException(ErrorCode.AUTHORIZATION_BAD_REQUEST, "본인이 등록한 문제만 삭제할 수 있습니다.");
                    }
                    reviewNoteProblemRepository.deleteById(id);
                    Image image = imageRepository.findByImageKey(problem.getImageKey())
                            .orElseThrow(() -> new NotFoundException(ErrorCode.IMAGE_NOT_FOUND));
                    imageRepository.deleteById(image.getId());
                    s3Utils.deleteS3Object(image.getFilename());
                });
    }


    /**
     * 주어진 날짜 범위 내에서 틀린 문제 기록을 찾습니다.
     *
     * @param request 날짜 범위 요청 데이터를 포함하는 WrongProblemRecordRequest 객체.
     * @return WrongProblemRecordResponse 객체 목록으로, 각 틀린 문제의 기록을 포함합니다.
     */
    public List<IncorrectProblemForReviewNoteRequest> findWrongProblemsByDateRange(IncorrectProblemRecordRequest request) {
        List<WrongProblemRecord> records = wrongProblemRecordRepository.findAllByWrongDateBetween(request.startDate(), request.endDate());
        return records.stream()
                .map(record -> IncorrectProblemForReviewNoteRequest.from(record))
                .collect(Collectors.toList());
    }
}
