package Chaeda_spring.domain.submission.homework.entity.homework;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class SlicingImageInPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imageUrl;

    private int problemNum;

    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    private SubmissionHomework submissionHomework;

    @Builder
    public SlicingImageInPage(String imageUrl, int problemNum, String answer) {
        this.imageUrl = imageUrl;
        this.problemNum = problemNum;
        this.answer = answer;
    }

    public void setSubmissionHomework(SubmissionHomework submissionHomework) {
        this.submissionHomework = submissionHomework;
    }
}
