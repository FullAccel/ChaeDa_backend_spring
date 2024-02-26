package Chaeda_spring.domain.image.dto;

import Chaeda_spring.domain.image.entity.ImageFileExtension;
import Chaeda_spring.domain.image.entity.ImageType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record ImageUploadRequest(
        @NotNull(message = "어떤 용도로 업로드한 이미지 타입인지 알려주세요.")
        @Schema(description = "이미지 파일의 타입", defaultValue = "ANNOUNCEMENT_THUMBNAIL")
        ImageType imageType,
        @NotNull(message = "이미지 파일의 확장자는 비워둘 수 없습니다.")
        @Schema(description = "이미지 파일의 확장자", defaultValue = "PNG")
        ImageFileExtension imageFileExtension
) {
}
