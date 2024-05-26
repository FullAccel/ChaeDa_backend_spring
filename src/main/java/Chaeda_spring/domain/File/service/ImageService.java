package Chaeda_spring.domain.File.service;

import Chaeda_spring.cloud_service_agents.s3.S3Utils;
import Chaeda_spring.domain.File.dto.ImageResponse;
import Chaeda_spring.domain.File.dto.UploadImageCompleteRequest;
import Chaeda_spring.domain.File.dto.UploadImageRequest;
import Chaeda_spring.domain.File.entity.Image;
import Chaeda_spring.domain.File.entity.ImageRepository;
import Chaeda_spring.domain.member.entity.Member;
import Chaeda_spring.domain.member.entity.MemberRepository;
import Chaeda_spring.global.constant.FileExtension;
import Chaeda_spring.global.constant.ImageType;
import Chaeda_spring.global.exception.NotFoundException;
import Chaeda_spring.global.utils.MemberUtils;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final AmazonS3 amazonS3;

    private final MemberRepository memberRepository;
    private final MemberUtils memberUtils;
    private final ImageRepository imageRepository;
    private final S3Utils s3Utils;


    /**
     * 현재 persigned url의 만료기간은 30분입니다.
     */
    private final int PRESIGNED_EXPIRATION = 1000 * 60 * 30;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    /**
     * 이 함수는 S3에 이미지 파일을 업로드 하기 위한 PUT용 presigned url을 한 개 생성합니다.
     *
     * @param request ImageType과 ImageFileExtension을 담고 있는 {@link UploadImageRequest} 객체
     * @return {@link ImageResponse}
     * @throws NotFoundException 해당 멤버가 존재하지 않을 시 발생합니다.
     **/
    public ImageResponse getImageUploadUrl(UploadImageRequest request) {

        Member member = memberUtils.getCurrentMember();

        String imageKey = generateUUID();
        return getImagePresignedUrlResponse(
                member.getId(),
                request.imageType(),
                request.fileExtension(),
                imageKey,
                HttpMethod.PUT
        );
    }

    /**
     * 이 함수는 S3에 이미지 파일을 업로드 하기 위한 PUT용 presigned url을 여러개 생성합니다.
     *
     * @param requests ImageType과 ImageFileExtension을 담고 있는 {@link UploadImageRequest} List
     * @return
     */
    public List<ImageResponse> getImageUploadUrlList(List<UploadImageRequest> requests) {

        Member member = memberUtils.getCurrentMember();

        return requests.stream()
                .map(request -> getImagePresignedUrlResponse(
                        member.getId(),
                        request.imageType(),
                        request.fileExtension(),
                        generateUUID(),
                        HttpMethod.PUT))
                .collect(Collectors.toList());
    }

    /**
     * 이 함수는 이미지 파일 이름으로 S3에 이미지 파일을 조회하고 해당 이미지의 GET용 presigned url을 요청 개수 만큼 생성합니다.
     *
     * @param requests ImageType과 ImageFileExtension을 담고 있습니다.
     * @return {@link ImageResponse}
     * @throws NotFoundException 해당 멤버가 존재하지 않을 시 발생합니다.
     **/
    public List<ImageResponse> getImageReadUrl(List<UploadImageCompleteRequest> requests) {
        Member member = memberUtils.getCurrentMember();

        return requests.stream()
                .map(request -> getImagePresignedUrlResponse(
                        member.getId(),
                        request.imageType(),
                        request.fileExtension(),
                        generateUUID(),
                        HttpMethod.GET))
                .collect(Collectors.toList());
    }

    /**
     * 이 함수는 서버로 직접 이미지 파일들을 전송 받아 S3에 업로드 합니다.
     *
     * @param files 이미지 파일 리스트입니다.
     * @return 파일이름 리스트를 전송합니다.
     */
    public ArrayList<String> uploadFile(MultipartFile[] files) {

        ArrayList<String> fileNames = new ArrayList<>();
        Member member = memberUtils.getCurrentMember();

        for (int i = 0; i < files.length; i++) {
            try {
                File fileObj = convertMultiPartFileToFile(files[i]);
                String originalFilename = files[i].getOriginalFilename();
                String imageType = originalFilename.split("-")[0];
                String imageFileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

                String imageKey = generateUUID();
                String fileName = createImageFileName(
                        member.getId(),
                        ImageType.from(imageType),
                        imageKey,
                        FileExtension.from(imageFileExtension));

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
     * 주어진 회원에 대해 이미지 업로드를 완료합니다.
     *
     * <p>이 메서드는 이미지 업로드 요청 목록을 처리하여 해당 회원에 대한 이미지 데이터를
     * 저장소에 저장합니다.</p>
     *
     * @param member   이미지를 업로드하는 회원
     * @param requests 이미지 세부 정보가 포함된 이미지 업로드 요청 목록
     */
    public void uploadImageComplete(Member member, List<UploadImageCompleteRequest> requests) {
        for (UploadImageCompleteRequest request : requests) {
            Image image = Image.builder()
                    .imageType(request.imageType())
                    .imageKey(request.imageKey())
                    .fileExtension(request.fileExtension())
                    .memberId(member.getId())
                    .build();
            imageRepository.save(image);
        }
    }

    /**
     * AWS S3에 해당 회원의 특정 이미지를 삭제합니다.
     *
     * @param image
     */
    public void deleteImageInS3(Image image) {
        String fileName = createImageFileName(
                image.getMemberId(),
                image.getImageType(),
                image.getImageKey(),
                image.getFileExtension()
        );
        amazonS3.deleteObject(new DeleteObjectRequest(bucketName, fileName));
    }

    public void deleteImageInDB(Image image) {
        imageRepository.deleteById(image.getId());
    }


    /**
     * 여기서부터는 재활용성을 위한 클래스 내에서 활용하는 메서드들 입니다.
     */

    public ImageResponse getImagePresignedUrlResponse(Long memberId, ImageType imageType, FileExtension extension, String imageKey, HttpMethod httpMethod) {
        String fileName = createImageFileName(
                memberId,
                imageType,
                imageKey,
                extension
        );

        String presignedUrl = s3Utils.getS3PresignedUrl(fileName, httpMethod);

        return ImageResponse.builder()
                .imageKey(imageKey)
                .presignedUrl(presignedUrl)
                .build();
    }

    public String getPresignedUrlByImage(Image image) {
        String fileName = createImageFileName(
                image.getMemberId(),
                image.getImageType(),
                image.getImageKey(),
                image.getFileExtension()
        );

        return s3Utils.getS3PresignedUrl(fileName, HttpMethod.GET);
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

    private String createImageFileName(
            Long memberId,
            ImageType imageType,
            String imageKey,
            FileExtension fileExtension
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
                + fileExtension.getUploadExtension();
    }
}

