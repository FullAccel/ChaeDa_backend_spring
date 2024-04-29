package Chaeda_spring.domain.textbook.service;

import Chaeda_spring.cloud_service_agents.s3.S3Utils;
import Chaeda_spring.domain.member.entity.Member;
import Chaeda_spring.domain.textbook.dto.TextbookResponse;
import Chaeda_spring.domain.textbook.dto.TextbookUrlResponse;
import Chaeda_spring.domain.textbook.dto.UploadTextbookFileRequest;
import Chaeda_spring.domain.textbook.entity.Textbook;
import Chaeda_spring.domain.textbook.entity.TextbookRespository;
import Chaeda_spring.external_component.textbook_preprocessing.service.PreprocessingService;
import Chaeda_spring.global.utils.MemberUtils;
import com.amazonaws.HttpMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TextbookService {

    private final MemberUtils memberUtils;
    private final TextbookRespository textbookRespository;
    private final S3Utils s3Utils;
    private final PreprocessingService preprocessingService;

    /**
     * 교과서 목록을 검색하고 반환합니다,.
     *
     * @return 교과서를 나타내는 {@link TextbookResponse} 객체의 목록입니다.
     */
    public List<TextbookResponse> getTextbookList() {
        return textbookRespository.findAll()
                .stream().map(TextbookResponse::of)
                .collect(Collectors.toList());
    }

    /**
     * 교과서 파일을 업로드하기 위해 pre-signed URL을 생성합니다.
     *
     * @param request 교과서 파일의 세부 정보가 포함된 요청 객체입니다.
     * @return 교과서 파일을 업로드하기 위해 pre-signed URL이 포함된 응답 객체입니다.
     */
    public TextbookUrlResponse createTextbookUploadUrl(UploadTextbookFileRequest request) {

        memberUtils.getCurrentMember();
        String textBookFileName = createTextBookFileName(request);
        return TextbookUrlResponse.builder()
                .presignedUrl(s3Utils.getS3PresignedUrl(textBookFileName, HttpMethod.PUT))
                .fileName(textBookFileName)
                .build();
    }

    /**
     * 제공된 요청에서 엔티티를 생성하고 현재 멤버와 연결하여 교재를 저장합니다,
     * 그리고 전처리를 위해 외부의 교재 전처리 서버와 통신합니다.
     *
     * @param request 저장할 교과서 파일의 세부 정보가 포함된 요청 객체입니다.
     */
    public void saveTextBook(UploadTextbookFileRequest request) {
        Member member = memberUtils.getCurrentMember();
        textbookRespository.save(Textbook.from(request));
        preprocessingService.sendFilenameToPreprocessingServer(createTextBookFileName(request));
    }

    /**
     * 요청 세부 정보를 기반으로 교재의 파일 이름을 생성합니다.
     *
     * @param dto 교재의 세부 정보가 포함된 요청 객체입니다.
     * @return 교재의 형식이 지정된 파일 이름입니다.
     */
    private String createTextBookFileName(UploadTextbookFileRequest dto) {
        return "textbook/" + dto.publishYear() + "/" + dto.name() + "." + dto.fileExtension().getValue();
    }


}
