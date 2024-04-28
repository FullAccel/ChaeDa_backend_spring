package Chaeda_spring.domain.textbook.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record TextbookUrlResponse(
        @Schema(description = "Presigned Url입니다. 해당 url을 통해 교재를 업로드해주세요")
        String presignedUrl,
        @Schema(description = "s3에 업로드될 해당 교재의 이름입니다.", example = "textbook/2024/쎈수학상.pdf")
        String fileName
) {
}
