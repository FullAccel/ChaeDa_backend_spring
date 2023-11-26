package Chaeda_spring.domain.textbook.dto;

import Chaeda_spring.domain.textbook.entity.Textbook;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TextbookResponseDto {

    private Long id;

    private String name;

    private String imageUrl;

    private int lastPageNum;

    private String targetGrade;

    @Builder
    public TextbookResponseDto(Textbook textbook) {
        this.id = textbook.getId();
        this.name = textbook.getName();
        this.imageUrl = textbook.getImageUrl();
        this.lastPageNum = textbook.getLastPageNum();
        this.targetGrade = textbook.getTargetGrade();
    }
}
