package Chaeda_spring.domain.submission.service;

import Chaeda_spring.domain.image.dto.ImageResponse;
import Chaeda_spring.domain.image.dto.UploadImageCompleteRequest;
import Chaeda_spring.domain.image.entity.Image;
import Chaeda_spring.domain.image.entity.ImageRepository;
import Chaeda_spring.domain.image.entity.ImageType;
import Chaeda_spring.domain.image.service.ImageService;
import Chaeda_spring.domain.announcement.entity.HwAnnouncementRepository;
import Chaeda_spring.domain.member.entity.MemberRepository;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.submission.entity.Submission;
import Chaeda_spring.domain.submission.entity.SubmissionRepository;
import Chaeda_spring.global.exception.ErrorCode;
import Chaeda_spring.global.exception.NotEqualsException;
import Chaeda_spring.global.exception.NotFoundException;
import com.amazonaws.HttpMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final MemberRepository memberRepository;
    private final HwAnnouncementRepository hwAnnouncementRepository;
    private final SubmissionRepository submissionRepository;
    private final ImageService imageService;
    private final ImageRepository imageRepository;

    /**
     * 이 함수는 클라이언트가 보낸 이미지 업로드 완료되었다고 알려주는 요청을 받고 이미지 정보를 DB에 저장합니다.
     * 이 함수가 호출이되면 숙제를 제출한 것이며 이후 Submission의 상태를 제출 완료로 변경합니다.
     * 실제로 S3에 저장된 이미지만 DB에 기록하기 위한 함수입니다.
     *
     * @param studentId 해당 이미지를 업로드한 멤버의 Id입니다.
     * @param requests  이미지의 타입, 키, 파일 확장자 정보를 담고 있는 {@link UploadImageCompleteRequest} 객체
     * @return 이미지 정보 저장 성공 여부를 나타내는 boolean 값. 현재 구현에서는 항상 {@code true}를 반환합니다.
     * @throws NotFoundException  해당 멤버가 존재하지 않을 시 발생합니다.
     * @throws NotEqualsException ErrorCode : IS_NOT_STUDENT, 학생 회원만 숙제를 제출할 수 있습니다
     */
    @Transactional
    public List<ImageResponse> uploadImageCompleteForSubmission(Long studentId, Long HwAnnouncementId, List<UploadImageCompleteRequest> requests) {

        Student student;
        try {
            student = (Student) memberRepository.findById(studentId)
                    .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND, "해당 Id의 학생이 존재하지 않습니다."));

        } catch (ClassCastException e) {
            throw new NotEqualsException(ErrorCode.IS_NOT_STUDENT);
        }

        for (UploadImageCompleteRequest request : requests) {
            Image image = Image.builder()
                    .imageType(request.imageType())
                    .imageKey(request.imageKey())
                    .imageFileExtension(request.imageFileExtension())
                    .memberId(studentId)
                    .build();
            Image savedImage = imageRepository.save(image);

            if (request.imageType().equals(ImageType.HOMEWORK_SUBMISSION)) {
                Submission submission = submissionRepository.findByHwAnnouncementIdAndStudentId(HwAnnouncementId, studentId);
                submission.completeHomework();
                submission.getHwAnnouncement().plusSubmissionNum();
                savedImage.setSubmission(submission);
            } else
                throw new NotEqualsException(ErrorCode.IMAGE_TYPE_INCORRECT, "해당 요청은 HOMEWORK_SUBMISSION 타입 이미지만 받을 수 있습니다");
        }

        return requests.stream()
                .map(request -> imageService.getImagePresignedUrlResponse(
                        studentId,
                        request.imageType(),
                        request.imageFileExtension(),
                        request.imageKey(),
                        HttpMethod.GET))
                .collect(Collectors.toList());
    }


    /**
     * 이 함수는 회원이 자신이 제출한 숙제사진을 조회할 수 있는 presigned url list를 리턴합니다.
     *
     * @param studentId        해당 이미지를 전송하는 멤버의 Id입니다.
     * @param hwAnnouncementId 제출 이미지를 확인하고 싶은 숙제 공지 Id입니다.
     * @return 자신이 제출한 숙제사진을 조회할 수 있는 presigned url list
     * @throws NotFoundException  case 1 : 해당 멤버가 존재하지 않을 시 발생합니다.
     *                            case 2 : 해당 회원이 숙제 제출을 하지 않았을 시 발생합니다.
     * @throws NotEqualsException ErrorCode : IS_NOT_STUDENT, 학생 회원만 숙제를 제출할 수 있습니다
     */
    public List<ImageResponse> getSubmissionImages(Long studentId, Long hwAnnouncementId) {
        Student student;
        try {
            student = (Student) memberRepository.findById(studentId)
                    .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND, "해당 Id의 학생이 존재하지 않습니다."));

        } catch (ClassCastException e) {
            throw new NotEqualsException(ErrorCode.IS_NOT_STUDENT);
        }
        hwAnnouncementRepository.findById(hwAnnouncementId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.ANNOUNCEMENT_NOT_FOUND));

        Submission submission = submissionRepository.findByHwAnnouncementIdAndStudentId(hwAnnouncementId, studentId);

        if (!submission.isCompleteStatus())
            throw new NotFoundException(ErrorCode.HOMEWORK_NOT_COMPLETE);

        return submission.getImageList().stream()
                .map(image -> imageService.getImagePresignedUrlResponse(
                        studentId,
                        image.getImageType(),
                        image.getImageFileExtension(),
                        image.getImageKey(),
                        HttpMethod.GET))
                .collect(Collectors.toList());
    }
}
