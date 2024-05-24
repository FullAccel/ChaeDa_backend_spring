package Chaeda_spring.domain.review_note.service;

import Chaeda_spring.domain.image.dto.ImageResponse;
import Chaeda_spring.domain.image.dto.UploadImageCompleteRequest;
import Chaeda_spring.domain.image.service.ImageService;
import Chaeda_spring.domain.member.entity.Member;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.review_note.dto.ReviewNoteProblemIdRequest;
import Chaeda_spring.domain.review_note.dto.ReviewNoteProblemInfo;
import Chaeda_spring.domain.review_note.dto.ReviewNoteProblemResponse;
import Chaeda_spring.domain.review_note.entity.*;
import Chaeda_spring.domain.textbook.entity.Textbook;
import Chaeda_spring.domain.textbook.entity.TextbookRespository;
import Chaeda_spring.global.exception.ErrorCode;
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
    private final ReviewNoteFolderRepository reviewNoteFolderRepository;
    private final ReviewNoteProblemFolderRepository reviewNoteProblemFolderRepository;
    private final TextbookRespository textbookRespository;
    private final ImageService imageService;

    /**
     * 사용자로부터 수신된 정보를 사용하여 ReviewNoteProblem을 생성하고 저장합니다.
     *
     * @param member  문제를 생성하는 학생 관련 회원 정보
     * @param request 문제 생성에 필요한 정보를 포함하는 요청 객체
     * @throws NotFoundException 해당 텍스트북을 찾지 못한 경우 예외를 발생시킵니다.
     */
    public void createReviewNoteProblem(Member member, ReviewNoteProblemInfo request) {

        Textbook textbook = textbookRespository.findById(request.textbookId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.TEXTBOOK_NOT_FOUND));

        reviewNoteProblemRepository.save(
                ReviewNoteProblem.of((Student) member, request, textbook)
        );
    }

    public List<ReviewNoteProblemResponse> getProblemsInInCorrectStorage(Member member) {
        List<ReviewNoteProblem> reviewNoteProblemList = reviewNoteProblemRepository.findAllByStudent((Student) member);
        List<UploadImageCompleteRequest> imageRequest = reviewNoteProblemList.stream()
                .map(problem -> UploadImageCompleteRequest.of(
                        problem.getImageType(),
                        problem.getImageFileExtension(),
                        problem.getImageKey()
                )).collect(Collectors.toList());

        List<ImageResponse> imageReadUrls = imageService.getImageReadUrl(imageRequest);

        List<ReviewNoteProblemResponse> response = new ArrayList<>();
        for (int i = 0; i < reviewNoteProblemList.size(); i++) {
            response.add(ReviewNoteProblemResponse.of(reviewNoteProblemList.get(i), imageReadUrls.get(i)));
        }
        return response;
    }

    public Long createReviewNoteFolder(Member member, ReviewNoteProblemIdRequest request) {

        ReviewNoteFolder folder = ReviewNoteFolder.builder()
                .title(request.title())
                .description(request.description())
                .student((Student) member)
                .build();

        List<ReviewNoteProblemFolder> problemFolderMappingList = new ArrayList<>();

        request.reviewNoteProblemIds().stream()
                .forEach(id -> {

                    ReviewNoteProblem problem = reviewNoteProblemRepository.findById(id)
                            .orElseThrow(() -> new NotFoundException(ErrorCode.PROBLEM_NOT_FOUND));
                    ReviewNoteProblemFolder mapper = ReviewNoteProblemFolder.builder()
                            .reviewNoteFolder(folder)
                            .reviewNoteProblem(problem)
                            .build();
                    reviewNoteProblemFolderRepository.save(mapper);


                    problemFolderMappingList.add(mapper);

                });

        folder.mappingProblemsToFolder(problemFolderMappingList);
        return reviewNoteFolderRepository.save(folder).getId();
    }

}
