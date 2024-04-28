package Chaeda_spring.cloud_service_agents.s3;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class S3Utils {

    private final AmazonS3 amazonS3;
    /**
     * 현재 persigned url의 만료기간은 30분입니다.
     */
    private final int PRESIGNED_EXPIRATION = 1000 * 60 * 30;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public String getS3PresignedUrl(String fileName, HttpMethod httpMethod) {

        // S3에 presigned url을 특정 Signature에 맞게 생성해달라는 요청 url을 생성합니다.
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, fileName)
                        .withMethod(httpMethod)
                        .withExpiration(getPreSignedUrlExpiration());

//        generatePresignedUrlRequest.addRequestParameter(
//                Headers.S3_CANNED_ACL, CannedAccessControlList.PublicRead.toString());

        return amazonS3.generatePresignedUrl(generatePresignedUrlRequest).toString();
    }

    public Date getPreSignedUrlExpiration() {
        Date expiration = new Date();
        var expTimeMillis = expiration.getTime();
        expTimeMillis += PRESIGNED_EXPIRATION;
        expiration.setTime(expTimeMillis);
        return expiration;
    }
}
