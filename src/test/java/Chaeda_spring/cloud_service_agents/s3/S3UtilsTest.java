package Chaeda_spring.cloud_service_agents.s3;


public class S3UtilsTest {
//    @InjectMocks
//    private S3Utils s3Utils;
//
//    @Mock
//    private AmazonS3 amazonS3;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void getS3PresignedUrl() throws Exception {
//        String testFileName = "testFile";
//        HttpMethod testMethod = HttpMethod.GET;
//        String testBucketName = "s3-fullaccel";
//        URL testUrl = new URL("https://amazonaws.com/s3bucket/testFile");
//
//        GeneratePresignedUrlRequest testRequest =
//                new GeneratePresignedUrlRequest(testBucketName, testFileName)
//                        .withMethod(testMethod)
//                        .withExpiration(s3Utils.getPreSignedUrlExpiration());
//
//
//        when(amazonS3.generatePresignedUrl(testRequest)).thenReturn(testUrl);
//
//        String result = s3Utils.getS3PresignedUrl(testFileName, testMethod);
//
//        assertEquals(testUrl.toString(), result);
//    }
}