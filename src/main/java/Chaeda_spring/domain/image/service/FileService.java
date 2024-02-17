package Chaeda_spring.domain.image.service;

import Chaeda_spring.domain.image.dto.PresignedUrlRequest;
import Chaeda_spring.domain.image.dto.PresignedUrlResponse;
import Chaeda_spring.domain.image.dto.UploadCompleteRequest;
import Chaeda_spring.domain.image.entity.Image;
import Chaeda_spring.domain.image.entity.ImageFileExtension;
import Chaeda_spring.domain.image.entity.ImageRepository;
import Chaeda_spring.domain.image.entity.ImageType;
import Chaeda_spring.domain.member.entity.Member;
import Chaeda_spring.domain.member.entity.MemberRepository;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final AmazonS3 amazonS3;

    private final MemberRepository memberRepository;

    private final ImageRepository imageRepository;

    private final int PRESIGNED_EXPIRATION = 1000 * 60 * 30; //30분

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public PresignedUrlResponse generatePresignedUrl(Long memberId, PresignedUrlRequest request) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("해당 Id의 멤버가 존재하지 않습니다."));

        String imageKey = generateUUID();
        String fileName = createFileName(
                memberId,
                ImageType.HOMEWORK_THUMBNAIL,
                imageKey,
                request.imageFileExtension());

        GeneratePresignedUrlRequest generatePresignedUrlRequest = createGeneratePreSignedUrlRequest(
                bucketName,
                fileName,
                request.imageFileExtension().toString()
        );

        String presignedUrl = amazonS3.generatePresignedUrl(generatePresignedUrlRequest).toString();

        return PresignedUrlResponse.builder()
                .imageKey(imageKey)
                .presigendUrl(presignedUrl)
                .build();
    }

    public boolean uploadImageComplete(Long memberId, UploadCompleteRequest request) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("해당 Id의 멤버가 존재하지 않습니다."));

        Image image = Image.builder()
                .imageType(request.imageType())
                .imageKey(request.imageKey())
                .imageFileExtension(request.imageFileExtension())
                .memberId(memberId)
                .build();
        imageRepository.save(image);

        return true;
    }

    public List<String> getFileUrl(String[] filenames) {

        List<String> urls = new ArrayList<>();
        Date preSignedUrlExpiration = getPreSignedUrlExpiration();

        for (String filename : filenames) {
            System.out.println("넘어오는 파일명 : " + filename);
            GeneratePresignedUrlRequest generatePresignedUrlRequest =
                    new GeneratePresignedUrlRequest(bucketName, (filename).replace(File.separatorChar, '/'))
                            .withMethod(HttpMethod.GET)
                            .withExpiration(preSignedUrlExpiration);
            urls.add(amazonS3.generatePresignedUrl(generatePresignedUrlRequest).toString());
        }
        return urls;
    }

    public void uploadFile(MultipartFile file) throws IOException {
        File fileObj = convertMultiPartFileToFile(file);
        // 파일을 amazonS3에 업로드합니다.
        amazonS3.putObject(new PutObjectRequest(bucketName, file.getOriginalFilename(), fileObj));
        fileObj.delete(); // To clean up the temporary file
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

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }

    private String createFileName(
            Long memberId,
            ImageType imageType,
            String imageKey,
            ImageFileExtension imageFileExtension
    ) {
        return imageType.getValue()
                + "/"
                + memberId
                + "/"
                + imageKey
                + "."
                + imageFileExtension.getUploadExtension();
    }

    private GeneratePresignedUrlRequest createGeneratePreSignedUrlRequest(
            String bucket, String fileName, String fileExtension) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucket, fileName, HttpMethod.PUT)
                        .withKey(fileName)
                        .withContentType("image/" + fileExtension)
                        .withExpiration(getPreSignedUrlExpiration());

        generatePresignedUrlRequest.addRequestParameter(
                Headers.S3_CANNED_ACL, CannedAccessControlList.PublicRead.toString());

        return generatePresignedUrlRequest;
    }

    private Date getPreSignedUrlExpiration() {
        Date expiration = new Date();
        var expTimeMillis = expiration.getTime();
        expTimeMillis += PRESIGNED_EXPIRATION;
        expiration.setTime(expTimeMillis);
        return expiration;
    }
}

