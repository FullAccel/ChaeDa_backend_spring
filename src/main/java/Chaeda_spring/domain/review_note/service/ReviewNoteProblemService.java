package Chaeda_spring.domain.review_note.service;

import Chaeda_spring.cloud_service_agents.s3.S3Utils;
import Chaeda_spring.domain.File.dto.ImageResponse;
import Chaeda_spring.domain.File.dto.PresignedUrlResponse;
import Chaeda_spring.domain.File.dto.UploadImageCompleteRequest;
import Chaeda_spring.domain.File.entity.File;
import Chaeda_spring.domain.File.entity.FileRepository;
import Chaeda_spring.domain.File.entity.Image;
import Chaeda_spring.domain.File.entity.ImageRepository;
import Chaeda_spring.domain.File.service.ImageService;
import Chaeda_spring.domain.member.entity.Member;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.review_note.dto.*;
import Chaeda_spring.domain.review_note.entity.*;
import Chaeda_spring.external_component.review_note_maker.ReviewNoteMakerService;
import Chaeda_spring.global.constant.FileExtension;
import Chaeda_spring.global.exception.ErrorCode;
import Chaeda_spring.global.exception.NotEqualsException;
import Chaeda_spring.global.exception.NotFoundException;
import com.amazonaws.HttpMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewNoteProblemService {

    private final ReviewNoteProblemRepository reviewNoteProblemRepository;
    private final ReviewNoteFolderRepository reviewNoteFolderRepository;
    private final ReviewNoteProblemFolderRepository reviewNoteProblemFolderRepository;
    private final ImageService imageService;
    private final ReviewNoteMakerService reviewNoteMakerService;
    private final FileRepository fileRepository;
    private final S3Utils s3Utils;
    private final ImageRepository imageRepository;

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
     * @param member
     * @return
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

    public Long createReviewNoteFolder(Member member, ReviewNoteProblemIdRequest request) {

        ReviewNoteFolder folder = ReviewNoteFolder.builder()
                .title(request.title())
                .description(request.description())
                .student((Student) member)
                .build();

        List<ReviewNoteProblemFolder> problemFolderMappingList = new ArrayList<>();
        ReviewNoteFolder saved = reviewNoteFolderRepository.save(folder);
        request.reviewNoteProblemIds().stream()
                .forEach(id -> {
                    ReviewNoteProblem problem = reviewNoteProblemRepository.findById(id)
                            .orElseThrow(() -> new NotFoundException(ErrorCode.PROBLEM_NOT_FOUND));
                    ReviewNoteProblemFolder mapper = ReviewNoteProblemFolder.builder()
                            .reviewNoteFolder(saved)
                            .reviewNoteProblem(problem)
                            .build();
                    reviewNoteProblemFolderRepository.save(mapper);

                    problemFolderMappingList.add(mapper);
                });

        folder.mappingProblemsToFolder(problemFolderMappingList);
        return saved.getId();
    }


    public Long createReviewNotePDF(Member member, Long reviewNoteFolderId) {

        ReviewNoteFolder folder = reviewNoteFolderRepository.findById(reviewNoteFolderId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.REVIEW_NOTE_NOT_FOUND));

        List<ReviewNoteProblemInfo> problemInfoList = folder.getReviewNoteProblemFolders().stream()
                .map(mapper -> ReviewNoteProblemInfo.from(mapper.getReviewNoteProblem()))
                .collect(Collectors.toList());

        LocalDateTime now = LocalDateTime.now();
        String filename = getFilename(member, now, folder);

        reviewNoteMakerService.sendProblemInfoToPreprocessingServer(problemInfoList, filename, member);

        return fileRepository.save(File.builder()
                .title(folder.getTitle())
                .fileSrcName(filename)
                .createdDateTime(now)
                .member(member)
                .fileExtension(FileExtension.PDF)
                .build()).getId();
    }

    public List<ReviewNotePDFInfo> getReviewNotePDFList(Member member) {

        return fileRepository.findAllByMember(member).stream()
                .map(file -> ReviewNotePDFInfo.of(file))
                .collect(Collectors.toList());
    }

    public List<ReviewNoteProblemResponse> getProblemListInFolder(Member member, Long folderId) {

        List<ReviewNoteProblem> reviewNoteProblemList = reviewNoteFolderRepository.findById(folderId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.REVIEW_NOTE_NOT_FOUND))
                .getReviewNoteProblemFolders().stream()
                .map(mapper -> mapper.getReviewNoteProblem())
                .collect(Collectors.toList());

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

    public List<ReviewNoteFolderInfo> getReviewNoteFolderList(Member member) {

        return reviewNoteFolderRepository.findAllByStudent((Student) member).stream()
                .map(enity -> ReviewNoteFolderInfo.from(enity))
                .collect(Collectors.toList());
    }

    /**
     * 오답 노트 PDF 파일의 presigned URL을 가져옵니다.
     *
     * @param member          The member associated with the file.
     * @param reviewNotePDFId The ID of the review note PDF file.
     * @return The PresignedUrlResponse containing the presigned URL of the file.
     * @throws NotFoundException If the file cannot be found.
     */
    public PresignedUrlResponse getReviewNotePDFUrl(Member member, Long reviewNotePDFId) {

        File file = fileRepository.findById(reviewNotePDFId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.FILE_NOT_FOUND));
        return PresignedUrlResponse.from(s3Utils.getS3PresignedUrl(file.getFileSrcName(), HttpMethod.GET));
    }

    /**
     * 오답 폴더에 문제 추가하기
     *
     * @param folderId
     * @param reviewNoteProblemIds
     */
    public void addProblemToFolder(Long folderId, List<Long> reviewNoteProblemIds) {

        ReviewNoteFolder folder = reviewNoteFolderRepository.findById(folderId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.REVIEW_NOTE_FOLDER_NOT_FOUND));

        reviewNoteProblemIds.stream()
                .map(id -> reviewNoteProblemRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(ErrorCode.PROBLEM_NOT_FOUND)))
                .forEach(problem -> {
                    reviewNoteProblemFolderRepository.save(ReviewNoteProblemFolder.builder()
                            .reviewNoteProblem(problem)
                            .reviewNoteFolder(folder)
                            .build());
                });

        reviewNoteFolderRepository.save(folder);
    }

    /**
     * 지정된 문제 ID를 지정된 오답 노트 폴더에서 삭제합니다.
     *
     * @param folderId             The ID of the review note folder to delete the problems from.
     * @param reviewNoteProblemIds The IDs of the problems to be deleted.
     * @throws NotFoundException If the review note folder cannot be found.
     */
    public void deleteProblemFromFolder(Long folderId, List<Long> reviewNoteProblemIds) {
        ReviewNoteFolder folder = reviewNoteFolderRepository.findById(folderId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.REVIEW_NOTE_FOLDER_NOT_FOUND));

        reviewNoteProblemIds.stream()
                .map(id -> reviewNoteProblemRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(ErrorCode.PROBLEM_NOT_FOUND)))
                .forEach(problem -> {
                    reviewNoteProblemFolderRepository.deleteByReviewNoteProblemAndReviewNoteFolder(problem, folder);

                });
    }

    /**
     * 지정된 폴더 ID를 가진 폴더를 삭제합니다.
     *
     * @param folderId The ID of the folder to be deleted.
     * @throws NotFoundException If the folder cannot be found.
     */
    public void deleteFolder(Long folderId) {
        ReviewNoteFolder folder = reviewNoteFolderRepository.findById(folderId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.REVIEW_NOTE_FOLDER_NOT_FOUND));

        reviewNoteFolderRepository.deleteById(folderId);
    }

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


    private String getFilename(Member member, LocalDateTime now, ReviewNoteFolder folder) {
        return "review-note/" + member.getId() + "/" + now + "/" + folder.getTitle() + ".pdf";
    }
}
