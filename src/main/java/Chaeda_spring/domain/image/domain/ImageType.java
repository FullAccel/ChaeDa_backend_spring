package Chaeda_spring.domain.image.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImageType {
    HOMEWORK_THUMBNAIL("homework_thumbnail"),
    ANNOUNCEMENT_THUMBNAIL("announcement_thumbnail"),
    MEMBER_PROFILE("member_profile"),
    ;
    private final String value;
}
