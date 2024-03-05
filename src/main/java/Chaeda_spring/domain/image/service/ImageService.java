package Chaeda_spring.domain.image.service;

import Chaeda_spring.domain.image.dto.ImageUploadRequest;
import Chaeda_spring.domain.image.dto.PresignedUrlResponse;
import Chaeda_spring.domain.image.dto.UploadReadRequest;
import Chaeda_spring.domain.image.entity.Image;
import Chaeda_spring.domain.image.entity.ImageFileExtension;
import Chaeda_spring.domain.image.entity.ImageRepository;
import Chaeda_spring.domain.image.entity.ImageType;
import Chaeda_spring.domain.member.entity.MemberRepository;
import Chaeda_spring.domain.submission.entity.SubmissionRepository;
import Chaeda_spring.global.exception.ErrorCode;
import Chaeda_spring.global.exception.NotFoundException;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final AmazonS3 amazonS3;

    private final MemberRepository memberRepository;

    private final ImageRepository imageRepository;

    private final SubmissionRepository submissionRepository;

    /**
     * 현재 persigned url의 만료기간은 30분입니다.
     */
    private final int PRESIGNED_EXPIRATION = 1000 * 60 * 30;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    /**
     * 이 함수는 S3에 이미지 파일을 업로드 하기 위한 PUT용 presigned url을 한 개 생성합니다.
     *
     * @param memberId 해당 이미지를 업로드하는 멤버의 Id입니다.
     * @param request  ImageType과 ImageFileExtension을 담고 있는 {@link ImageUploadRequest} 객체
     * @return {@link PresignedUrlResponse}
     * @throws NotFoundException 해당 멤버가 존재하지 않을 시 발생합니다.
     **/
    public PresignedUrlResponse createFileUploadUrl(Long memberId, ImageUploadRequest request) {

        memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND, "해당 Id의 멤버가 존재하지 않습니다."));

        String imageKey = generateUUID();
        return getPresignedUrlResponse(
                memberId,
                request.imageType(),
                request.imageFileExtension(),
                imageKey,
                HttpMethod.PUT
        );
    }

    /**
     * 이 함수는 S3에 이미지 파일을 업로드 하기 위한 PUT용 presigned url을 여러개 생성합니다.
     *
     * @param memberId 해당 이미지를 업로드하는 멤버의 Id입니다.
     * @param requests ImageType과 ImageFileExtension을 담고 있는 {@link ImageUploadRequest} List
     * @return
     */
    public List<PresignedUrlResponse> createFileUploadUrlList(Long memberId, List<ImageUploadRequest> requests) {

        memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND, "해당 Id의 멤버가 존재하지 않습니다."));

        return requests.stream()
                .map(request -> getPresignedUrlResponse(
                        memberId,
                        request.imageType(),
                        request.imageFileExtension(),
                        generateUUID(),
                        HttpMethod.PUT))
                .collect(Collectors.toList());
    }

    /**
     * 이 함수는 이미지 파일 이름으로 S3에 이미지 파일을 조회하고 해당 이미지의 GET용 presigned url을 요청 개수 만큼 생성합니다.
     *
     * @param memberId 해당 이미지를 업로드하는 멤버의 Id입니다.
     * @param requests ImageType과 ImageFileExtension을 담고 있습니다.
     * @return {@link PresignedUrlResponse}
     * @throws NotFoundException 해당 멤버가 존재하지 않을 시 발생합니다.
     **/
    public List<PresignedUrlResponse> getFileReadUrl(Long memberId, List<UploadReadRequest> requests) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND, "해당 Id의 멤버가 존재하지 않습니다."));

        return requests.stream()
                .map(request -> getPresignedUrlResponse(
                        memberId,
                        request.imageType(),
                        request.imageFileExtension(),
                        generateUUID(),
                        HttpMethod.GET))
                .collect(Collectors.toList());
    }

    /**
     * 이 함수는 서버로 직접 이미지 파일들을 전송 받아 S3에 업로드 합니다.
     *
     * @param files    이미지 파일 리스트입니다.
     * @param memberId 해당 이미지를 전송하는 멤버의 Id입니다.
     * @return 파일이름 리스트를 전송합니다.
     */
    public ArrayList<String> uploadFile(MultipartFile[] files, Long memberId) {

        ArrayList<String> fileNames = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            try {
                File fileObj = convertMultiPartFileToFile(files[i]);
                String originalFilename = files[i].getOriginalFilename();
                String imageType = originalFilename.split("-")[0];
                String imageFileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

                String imageKey = generateUUID();
                String fileName = createFileName(
                        memberId,
                        ImageType.from(imageType),
                        imageKey,
                        ImageFileExtension.from(imageFileExtension));

                // 파일을 Amazon S3에 업로드합니다.
                amazonS3.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
                fileObj.delete(); // 임시 파일 정리
                fileNames.add(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileNames;
    }

    /**
     * 여기서부터는 재활용성을 위한 클래스 내에서 활용하는 메서드들 입니다.
     */
    public PresignedUrlResponse getPresignedUrlResponse(Long memberId, ImageType imageType, ImageFileExtension extension, String imageKey, HttpMethod httpMethod) {
        String fileName = createFileName(
                memberId,
                imageType,
                imageKey,
                extension);

        // S3에 presigned url을 특정 Signature에 맞게 생성해달라는 요청 url을 생성합니다.
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, fileName)
                        .withMethod(httpMethod)
//                        .withKey(fileName)
//                        .withContentType("image/" + fileExtension)
                        .withExpiration(getPreSignedUrlExpiration());

        //        generatePresignedUrlRequest.addRequestParameter(
//                Headers.S3_CANNED_ACL, CannedAccessControlList.PublicRead.toString());

        String presignedUrl = amazonS3.generatePresignedUrl(generatePresignedUrlRequest).toString();

        return PresignedUrlResponse.builder()
                .imageKey(imageKey)
                .presignedUrl(presignedUrl)
                .build();
    }

    public String getPresignedUrlByImage(Image image) {

        return getPresignedUrlResponse(
                image.getMemberId(),
                image.getImageType(),
                image.getImageFileExtension(),
                image.getImageKey(),
                HttpMethod.GET
        ).presignedUrl();
    }

    private File convertMultiPartFileToFile(MultipartFile file) throws IOException {
        // 프로젝트의 루트 디렉토리 경로를 가져옵니다.
        String rootPath = System.getProperty("user.dir");
        // 업로드된 파일의 원본 이름을 가져옵니다.
        String originalFileName = file.getOriginalFilename();
        // 파일을 저장할 전체 경로를 구성합니다.
        String fullPath = rootPath + File.separator + (originalFileName != null ? originalFileName : "defaultFileName");

        File convFile = new File(fullPath);
        // MultipartFile의 내용을 새로 생성된 File 객체로 복사합니다.
        file.transferTo(convFile);
        return convFile;
    }

    public String generateUUID() {
        return UUID.randomUUID().toString();
    }

    private String createFileName(
            Long memberId,
            ImageType imageType,
            String imageKey,
            ImageFileExtension imageFileExtension
    ) {
        return imageType.getValue()
                +
                "/"
                +
                memberId
                +
                "/"
                +
                imageKey
                + "."
                + imageFileExtension.getUploadExtension();
    }

    private Date getPreSignedUrlExpiration() {
        Date expiration = new Date();
        var expTimeMillis = expiration.getTime();
        expTimeMillis += PRESIGNED_EXPIRATION;
        expiration.setTime(expTimeMillis);
        return expiration;
    }
}

