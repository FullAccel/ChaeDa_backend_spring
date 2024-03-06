package Chaeda_spring.domain.image.dto;

import Chaeda_spring.domain.image.entity.Image;
import io.swagger.v3.oas.annotations.media.Schema;

public record GetImageResponse(
        @Schema(description = "이미지 파일의 고유 키 값", example = "10a99bab-4940-48af-92e7-867a56d6ec79")
        String imageKey,
        @Schema(description = "Presigned Url입니다. 해당 url을 통해 객체를 업로드해주세요")
        String presignedUrl,
        @Schema(description = "해당 이미지를 수정 혹은 삭제할 때 사용합니다")
        Long imageId
) {
    public static GetImageResponse from(ImageResponse imageResponse, Image image) {
        return new GetImageResponse(imageResponse.imageKey(), imageResponse.presignedUrl(), image.getId());
    }
}
