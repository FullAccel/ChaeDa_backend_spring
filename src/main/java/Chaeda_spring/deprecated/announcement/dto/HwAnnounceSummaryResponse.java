package Chaeda_spring.deprecated.announcement.dto;

import Chaeda_spring.deprecated.announcement.entity.HwAnnouncement;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record HwAnnounceSummaryResponse(
        @Schema(example = "1")
        Long id,
        @Schema(example = "숙제 공지 제목")
        String title,
        @Schema(example = "50")
        int startPage,
        @Schema(example = "56")
        int endPage,
        @Schema(example = "https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_profile/%E1%84%8A%E1%85%A6%E1%86%AB_%E1%84%89%E1%85%AE%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%A1%E1%86%BC_2024.jpeg")
        String bookImageUrl
) {
    public static HwAnnounceSummaryResponse of(HwAnnouncement entity) {
        return HwAnnounceSummaryResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .bookImageUrl(entity.getTextbook().getTextbookThumbnail())
                .startPage(entity.getStartPage())
                .endPage(entity.getEndPage())
                .build();
    }
}
