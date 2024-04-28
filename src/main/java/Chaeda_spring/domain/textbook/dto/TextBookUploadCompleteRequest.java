package Chaeda_spring.domain.textbook.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record TextBookUploadCompleteRequest(
        @Schema(description = "s3에 업로드될 해당 교재의 이름입니다.", example = "textbook/2024/쎈수학상.pdf")
        String fileName
) {
}
