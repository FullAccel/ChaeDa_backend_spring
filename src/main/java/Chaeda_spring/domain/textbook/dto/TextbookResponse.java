package Chaeda_spring.domain.textbook.dto;

import Chaeda_spring.domain.textbook.entity.Textbook;
import Chaeda_spring.global.constant.Grade;
import Chaeda_spring.global.constant.Subject;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.Year;

@Builder
public record TextbookResponse(
        @Schema(example = "1")
        Long id,
        @Schema(example = "쎈_고등수학상")
        String name,
        @Schema(example = "https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_profile/%E1%84%8A%E1%85%A6%E1%86%AB_%E1%84%89%E1%85%AE%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%A1%E1%86%BC_2024.jpeg")
        String imageUrl,
        @Schema(description = "대상 학년", example = "고1")
        Grade targetGrade,
        @Schema(description = "과목명", example = "수학 상")
        Subject subject,
        @Schema(description = "출판년도", example = "2024")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
        Year publishYear,
        @Schema(description = "출판사 이름", example = "좋은책 신사고")
        String publisher,
        @Schema(description = "문제 시작 페이지", example = "10")
        int startPage,
        @Schema(description = "문제 등장 마지막 페이지", example = "210")
        int lastPage

) {

    public static TextbookResponse of(Textbook textbook) {
        return TextbookResponse.builder()
                .id(textbook.getId())
                .name(textbook.getName())
                .imageUrl(textbook.getTextbookThumbnail())
                .targetGrade(textbook.getTargetGrade())
                .subject(textbook.getSubject())
                .publishYear(textbook.getPublishYear())
                .publisher(textbook.getPublisher())
                .startPage(textbook.getStartPageNum())
                .lastPage(textbook.getLastPageNum())
                .build();
    }
}
