package Chaeda_spring.external_component.textbook_preprocessing.service;

import Chaeda_spring.external_component.textbook_preprocessing.dto.FilenameRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class PreprocessingService {

    private final String serverUrl = "http://localhost:8000/textbook/preprocessing";

    /**
     * 파일명과 함께 POST 요청을 전송하여 서버와 통신합니다.
     *
     * @param filename 서버와 통신할 파일의 이름입니다.
     */
    public void sendFilenameToPreprocessingServer(String filename) {
        RestTemplate restTemplate = new RestTemplate();

        FilenameRequest request = new FilenameRequest(filename);
        HttpEntity<FilenameRequest> requestHttpEntity = new HttpEntity<>(request);
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(serverUrl, requestHttpEntity, Void.class);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            log.error("요청이 실패하였습니다. 응답 코드: {}", responseEntity.getStatusCode());
        }
    }
}
