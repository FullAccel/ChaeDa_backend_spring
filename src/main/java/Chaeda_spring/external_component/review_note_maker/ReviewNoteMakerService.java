package Chaeda_spring.external_component.review_note_maker;

import Chaeda_spring.domain.review_note.dto.ReviewNoteProblemInfo;
import Chaeda_spring.external_component.review_note_maker.dto.ReviewNoteMakeRequest;
import Chaeda_spring.global.constant.UrlConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class ReviewNoteMakerService {

    private final String serverUrl = UrlConstants.FASTAPI_SERVER_URL + "/review-note";


    @Async
    public CompletableFuture<Boolean> sendProblemInfoToPreprocessingServer(List<ReviewNoteProblemInfo> request, String filename) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<ReviewNoteMakeRequest> requestHttpEntity = new HttpEntity<>(ReviewNoteMakeRequest.builder()
                .reviewNoteProblemInfoList(request)
                .filename(filename)
                .build());

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(serverUrl, requestHttpEntity, Void.class);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            log.error("요청이 실패하였습니다. 응답 코드: {}", responseEntity.getStatusCode());
            return CompletableFuture.completedFuture(false);
        }
        return CompletableFuture.completedFuture(true);
    }
}
