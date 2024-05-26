package Chaeda_spring.domain.review_note.dto;

import Chaeda_spring.domain.review_note.entity.ReviewNoteFolder;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReviewNoteFolderInfo(
        String title,
        String description,
        LocalDateTime createdDate
) {
    public static ReviewNoteFolderInfo from(ReviewNoteFolder entity) {
        return ReviewNoteFolderInfo.builder()
                .title(entity.getTitle())
                .createdDate(entity.getCreatedDate())
                .description(entity.getDescription())
                .build();
    }
}
