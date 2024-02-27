package Chaeda_spring.domain.announcement.dto;

import Chaeda_spring.domain.announcement.entity.HwAnnouncement;
import lombok.Builder;

@Builder
public record HwAnnounceSummaryResponse(
        Long id,
        String title,
        int startPage,
        int endPage,
        String bookImageUrl
) {
    public static HwAnnounceSummaryResponse of(HwAnnouncement entity) {
        return HwAnnounceSummaryResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .bookImageUrl(entity.getTextbook().getImageUrl())
                .startPage(entity.getStartPage())
                .endPage(entity.getEndPage())
                .build();
    }
}
