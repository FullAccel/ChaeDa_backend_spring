package Chaeda_spring.domain.announcement.dto;

import Chaeda_spring.domain.announcement.entity.HomeworkAnnouncement;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HwAnnouncementContentDto {

    private String imgUrl;
    private String content;

    public HwAnnouncementContentDto(HomeworkAnnouncement entity) {
        this.imgUrl = entity.getTextbookImageUrl();
        this.content = entity.getContent();
    }
}
