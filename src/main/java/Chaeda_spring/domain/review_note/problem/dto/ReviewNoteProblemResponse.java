package Chaeda_spring.domain.review_note.problem.dto;

import Chaeda_spring.domain.File.dto.ImageResponse;
import Chaeda_spring.domain.review_note.problem.entity.ReviewNoteProblem;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ReviewNoteProblemResponse(
        @Schema(example = "1")
        Long reviewNoteProblemId,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        @Schema(description = "문제를 틀린 날짜", example = "2024-05-22")
        LocalDate incorrectDate,
        @Schema(description = "이미지 파일의 고유 키 값", example = "10a99bab-4940-48af-92e7-867a56d6ec79")
        String imageKey,
        @Schema(description = "Presigned Url입니다. 해당 url을 통해 객체를 업로드해주세요")
        String presignedUrl
) {
    public static ReviewNoteProblemResponse of(ReviewNoteProblem reviewNoteProblem, ImageResponse imageResponse) {
        return ReviewNoteProblemResponse.builder()
                .reviewNoteProblemId(reviewNoteProblem.getId())
                .incorrectDate(reviewNoteProblem.getIncorrectDate())
                .imageKey(imageResponse.imageKey())
                .presignedUrl(imageResponse.presignedUrl())
                .build();
    }

}