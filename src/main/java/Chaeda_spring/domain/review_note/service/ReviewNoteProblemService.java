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
     * 리뷰 노트 폴더를 생성합니다.
     *
     * @param member  리뷰 노트 폴더를 생성할 회원.
     * @param request 리뷰 노트 폴더 생성에 필요한 요청 데이터.
     * @return 생성된 리뷰 노트 폴더의 ID.
     * @throws NotFoundException 주어진 문제 ID에 해당하는 문제가 존재하지 않을 경우 발생합니다.
     */
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

    /**
     * 리뷰 노트 폴더로부터 PDF 파일을 생성합니다.
     *
     * @param member             PDF 파일을 생성할 회원.
     * @param reviewNoteFolderId PDF 파일을 생성할 리뷰 노트 폴더의 ID.
     * @return 생성된 PDF 파일의 ID.
     * @throws NotFoundException 주어진 리뷰 노트 폴더 ID에 해당하는 폴더가 존재하지 않을 경우 발생합니다.
     */
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

    /**
     * 주어진 회원의 리뷰 노트 PDF 목록을 가져옵니다.
     *
     * @param member 리뷰 노트 PDF 목록을 가져올 회원.
     * @return ReviewNotePDFInfo 객체 목록으로, 각 PDF 파일의 정보를 포함합니다.
     */
    public List<ReviewNotePDFInfo> getReviewNotePDFList(Member member) {

        return fileRepository.findAllByMember(member).stream()
                .map(file -> ReviewNotePDFInfo.of(file))
                .collect(Collectors.toList());
    }

    /**
     * 주어진 오답 폴더에 있는 문제 목록을 가져옵니다.
     *
     * @param member   문제 목록을 가져올 회원.
     * @param folderId 문제 목록을 가져올 폴더의 ID.
     * @return ReviewNoteProblemResponse 객체 목록으로, 각 문제와 해당 이미지 URL을 포함합니다.
     * @throws NotFoundException 주어진 폴더 ID에 해당하는 폴더가 존재하지 않을 경우 발생합니다.
     */
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

    /**
     * 주어진 회원의 리뷰 노트 폴더 목록을 가져옵니다.
     *
     * @param member 리뷰 노트 폴더 목록을 가져올 회원.
     * @return ReviewNoteFolderInfo 객체 목록으로, 각 폴더의 정보를 포함합니다.
     */
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
     * 주어진 오답 폴더에 문제를 추가합니다.
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
     * PDF 파일의 파일명을 생성합니다.
     *
     * @param member 파일명을 생성할 회원.
     * @param now    생성 시간.
     * @param folder 파일명을 생성할 리뷰 노트 폴더.
     * @return 생성된 파일명 문자열.
     */
    private String getFilename(Member member, LocalDateTime now, ReviewNoteFolder folder) {
        return "review-note/" + member.getId() + "/" + now + "/" + folder.getTitle() + ".pdf";
    }
}
