package Chaeda_spring.domain.image.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

/**
 * JSON 리턴을 위한 이미지를 구별할 수 있는 고유 ImageKey와 Presigned Url 담은 Response 입니다
 *
 * @param imageKey
 * @param presignedUrl
 */
@Builder
public record PresignedUrlResponse(
        @Schema(description = "이미지 파일의 고유 키 값", example = "10a99bab-4940-48af-92e7-867a56d6ec79")
        String imageKey,
        @Schema(description = "Presigned Url입니다. 해당 url을 통해 객체를 업로드해주세요")
        String presignedUrl
) {
}
