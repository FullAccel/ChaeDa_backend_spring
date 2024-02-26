package Chaeda_spring.domain.image.entity;

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

    public static ImageType from(String value) {
        for (ImageType type : values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
