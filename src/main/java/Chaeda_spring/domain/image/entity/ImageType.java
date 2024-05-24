package Chaeda_spring.domain.image.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImageType {
    HOMEWORK_THUMBNAIL("homework_thumbnail"),
    ANNOUNCEMENT_THUMBNAIL("announcement_thumbnail"),
    HOMEWORK_SUBMISSION("homework_submission"),
    MEMBER_PROFILE("member_profile"),
    REVIEW_NOTE_PROBLEM("review_note_problem"),
    CLASS_GROUP_PROFILE("class_group_profile");
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
