package Chaeda_spring.domain.image.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record PresignedUrlResponse(
        @Schema(description = "이미지 파일의 고유 키 값. 업로드 완료시 해당 key를 같이 보내주세요", example = "10a99bab-4940-48af-92e7-867a56d6ec79")
        String imageKey,
        @Schema(description = "Presigned Url입니다. 해당 url을 통해 객체를 업로드해주세요")
        String presignedUrl
) {
}
