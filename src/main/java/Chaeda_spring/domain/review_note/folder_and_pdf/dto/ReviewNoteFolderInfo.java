package Chaeda_spring.domain.review_note.folder_and_pdf.dto;

import Chaeda_spring.domain.review_note.folder_and_pdf.entity.ReviewNoteFolder;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReviewNoteFolderInfo(
        Long id,
        String title,
        String description,
        LocalDateTime createdDate
) {
    public static ReviewNoteFolderInfo from(ReviewNoteFolder entity) {
        return ReviewNoteFolderInfo.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .createdDate(entity.getCreatedDate())
                .description(entity.getDescription())
                .build();
    }
}
