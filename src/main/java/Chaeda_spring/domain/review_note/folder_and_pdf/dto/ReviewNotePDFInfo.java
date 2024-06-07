package Chaeda_spring.domain.review_note.folder_and_pdf.dto;

import Chaeda_spring.domain.File.entity.File;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReviewNotePDFInfo(
        @Schema(example = "1")
        Long id,
        @Schema(example = "오답노트 제목")
        String title,
        @Schema(example = "yyyy-mm-ddTHH:MM:SS")
        LocalDateTime createdDateTime
) {
    public static ReviewNotePDFInfo of(File file) {
        return ReviewNotePDFInfo.builder()
                .id(file.getId())
                .title(file.getTitle())
                .createdDateTime(file.getCreatedDateTime())
                .build();
    }
}
