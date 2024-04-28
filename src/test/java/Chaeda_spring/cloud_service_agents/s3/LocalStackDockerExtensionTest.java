package Chaeda_spring.cloud_service_agents.s3;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;


@Slf4j
@Testcontainers
public class LocalStackDockerExtensionTest {

//    @Container
//    LocalStackContainer container = new LocalStackContainer()
//            .withServices(LocalStackContainer.Service.S3);

    @Test
    void test() throws Exception {
//        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
//                .withEndpointConfiguration(
//                        new AwsClientBuilder.EndpointConfiguration(
//                                container.getEndpointOverride(LocalStackContainer.Service.S3).toString(),
//                                Regions.AP_NORTHEAST_2.getName()
//                        ))
//                .withCredentials(new AWSStaticCredentialsProvider(
//                        new BasicAWSCredentials(
//                                container.getAccessKey(), container.getSecretKey()
//                        )
//                )).build();
//
//        String bucketName = "s3-fullaccel";
//        s3.createBucket(bucketName);
//        log.info("버킷을 생성했습니다. bucketName = {}", bucketName);
//
//        String content = "Hello S3";
//        String key = "s3-key";
//        s3.putObject(bucketName, key, content);
//        log.info("파일을 업로드하였습니다. bucketName = {}, key = {}, content = {}", bucketName, key, content);
//
//        List<String> results = IOUtils.readLines(s3.getObject(bucketName, key).getObjectContent(), "utf-8");
//        log.info("파일을 가져왔습니다. bucketName = {}, key = {}, results = {}", bucketName, key, results);
//
//        assertThat(results).hasSize(1);
//        assertThat(results.get(0)).isEqualTo(content);
    }
}
