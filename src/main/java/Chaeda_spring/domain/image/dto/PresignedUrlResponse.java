package Chaeda_spring.domain.image.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PresignedUrlResponse(
        @NotNull(message = "image key 값 입니다. 업로드 완료시 해당 key를 같이 보내주세요")
        @Schema(description = "이미지 파일의 고유 키 값")
        String imageKey,
        @NotNull(message = "Presigned Url입니다. 해당 url을 통해 객체를 업로드해주세요")
        @Schema(description = "이미지 파일의 업로드 경로 url")
        String presigendUrl
) {
}
