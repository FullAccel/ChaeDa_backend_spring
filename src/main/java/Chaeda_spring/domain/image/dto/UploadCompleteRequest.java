package Chaeda_spring.domain.image.dto;

import Chaeda_spring.domain.image.entity.ImageFileExtension;
import Chaeda_spring.domain.image.entity.ImageType;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@ApiModel
public record UploadCompleteRequest(

        @NotNull(message = "어떤 용도로 업로드한 이미지 타입인지 알려주세요.")
        @Schema(description = "이미지 파일의 타입", defaultValue = "ANNOUNCEMENT_THUMBNAIL")
        ImageType imageType,
        @NotNull(message = "이미지 파일의 확장자는 비워둘 수 없습니다.")
        @Schema(description = "이미지 파일의 확장자", defaultValue = "PNG")
        ImageFileExtension imageFileExtension,
        @NotBlank(message = "image key 값은 비워둘 수 없습니다")
        @Schema(description = "이미지 파일의 고유 키 값", defaultValue = "faaade62-5db1-46e4-a132-5c34da86cd09")
        String imageKey
) {
}
