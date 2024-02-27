package Chaeda_spring.domain.announcement.dto;

import Chaeda_spring.domain.announcement.entity.HwAnnouncement;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HwAnnouncementContentDto {

    private String imgUrl;
    private String content;

    public HwAnnouncementContentDto(HwAnnouncement entity) {
        this.imgUrl = entity.getTextbook().getImageUrl();
        this.content = entity.getContent();
    }
}
