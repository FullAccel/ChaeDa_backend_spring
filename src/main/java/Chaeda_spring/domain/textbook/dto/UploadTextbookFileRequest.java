package Chaeda_spring.domain.textbook.dto;

import Chaeda_spring.domain.textbook.entity.TextBookFileExtension;
import Chaeda_spring.global.constant.Grade;
import Chaeda_spring.global.constant.Subject;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Year;

public record UploadTextbookFileRequest(
        @Schema(description = "교재 이름", example = "쎈_고등수학상_2024")
        String name,
        @Schema(description = "문제가 처음으로 나오는 페이지", example = "11")
        int startPageNum,
        @Schema(description = "문제가 마지막으로 나오는 페이지", example = "224")
        int lastPageNum,
        @Schema(description = "출판사 이름", example = "좋은책 신사고")
        String publisher,
        @Schema(description = "대상 학년", example = "고1")
        Grade targetGrade,
        @Schema(description = "과목명", example = "수학1")
        Subject subject,
        @Schema(description = "출판년도", example = "2024")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
        Year publishYear,
        @Schema(description = "파일확장자", example = "pdf")
        TextBookFileExtension fileExtension
) {
}
