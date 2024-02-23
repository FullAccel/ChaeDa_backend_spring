package Chaeda_spring.global.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsS3Config {

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public AmazonS3 amazonS3() {
        AWSCredentials credentials =
                new BasicAWSCredentials(
                        accessKey, secretKey);

        return AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();

    }

//    @Bean
//    public AwsCredentials basicAWSCredentials() {
//        return AwsBasicCredentials.create(accessKey, secretKey);
//    }
//
//    @Bean
//    public S3Presigner s3Presigner(AwsCredentials awsCredentials) {
//        return S3Presigner.builder()
//                .region(Region.AP_NORTHEAST_2)
//                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
//                .build();
//    }
//
//    @Bean
//    public S3Client s3Client(AwsCredentials awsCredentials) {
//        return S3Client.builder()
//                .region(Region.AP_NORTHEAST_2)
//                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
//                .build();
//    }
}

