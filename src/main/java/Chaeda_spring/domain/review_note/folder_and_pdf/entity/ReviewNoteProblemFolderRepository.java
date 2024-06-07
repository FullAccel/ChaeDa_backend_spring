package Chaeda_spring.domain.review_note.folder_and_pdf.entity;

import Chaeda_spring.domain.review_note.problem.entity.ReviewNoteProblem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewNoteProblemFolderRepository extends JpaRepository<ReviewNoteProblemFolder, Long> {

    void deleteByReviewNoteProblemAndReviewNoteFolder(ReviewNoteProblem problem, ReviewNoteFolder folder);
}
