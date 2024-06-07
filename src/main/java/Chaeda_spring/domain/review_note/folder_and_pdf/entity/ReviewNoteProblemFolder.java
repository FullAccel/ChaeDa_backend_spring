package Chaeda_spring.domain.review_note.folder_and_pdf.entity;

import Chaeda_spring.domain.BaseTimeEntity;
import Chaeda_spring.domain.review_note.problem.entity.ReviewNoteProblem;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class ReviewNoteProblemFolder extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ReviewNoteProblem reviewNoteProblem;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ReviewNoteFolder reviewNoteFolder;

    @Builder
    public ReviewNoteProblemFolder(ReviewNoteProblem reviewNoteProblem, ReviewNoteFolder reviewNoteFolder) {
        this.reviewNoteProblem = reviewNoteProblem;
        this.reviewNoteFolder = reviewNoteFolder;
    }
}

