package Chaeda_spring.external_component.review_note_maker.dto;

import Chaeda_spring.domain.review_note.dto.ReviewNoteProblemInfo;
import lombok.Builder;

import java.util.List;

@Builder
public record ReviewNoteMakeRequest(
        String filename,
        Long memberId,
        List<ReviewNoteProblemInfo> reviewNoteProblemInfoList
) {
}
