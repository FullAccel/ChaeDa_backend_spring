package Chaeda_spring.domain.File.dto;

import Chaeda_spring.global.constant.FileExtension;
import Chaeda_spring.global.constant.ImageType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record UploadImageRequest(
        @NotNull(message = "어떤 용도로 업로드한 이미지 타입인지 알려주세요. 타입은 'HOMEWORK_THUMBNAIL', 'ANNOUNCEMENT_THUMBNAIL', 'HOMEWORK_SUBMISSION', 'MEMBER_PROFILE', 'CLASS_GROUP_PROFILE'이 있습니다.")
        @Schema(description = "이미지 파일의 타입", defaultValue = "HOMEWORK_SUBMISSION")
        ImageType imageType,
        @NotNull(message = "이미지 파일의 확장자는 비워둘 수 없습니다.")
        @Schema(description = "이미지 파일의 확장자", defaultValue = "PNG")
        FileExtension fileExtension
) {
}
