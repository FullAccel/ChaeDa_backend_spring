package Chaeda_spring.domain.textbook.dto;

import Chaeda_spring.domain.textbook.entity.Textbook;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record TextbookResponse(
        @Schema(example = "1")
        Long id,
        @Schema(example = "쎈_고등수학상_2024")
        String name,
        @Schema(example = "https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_profile/%E1%84%8A%E1%85%A6%E1%86%AB_%E1%84%89%E1%85%AE%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%A1%E1%86%BC_2024.jpeg")
        String imageUrl,
        @Schema(example = "224")
        int lastPageNum,
        @Schema(example = "고1")
        String targetGrade
) {

    public static TextbookResponse of(Textbook textbook) {
        return TextbookResponse.builder()
                .id(textbook.getId())
                .name(textbook.getName())
                .imageUrl(textbook.getImageUrl())
                .lastPageNum(textbook.getLastPageNum())
                .targetGrade(textbook.getTargetGrade())
                .build();
    }
}
