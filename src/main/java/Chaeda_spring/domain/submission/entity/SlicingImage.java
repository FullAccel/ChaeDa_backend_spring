package Chaeda_spring.domain.submission.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class SlicingImage {

    @Id
    @Column(name = "SLICING_IMAGE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imageUrl;

    private int problemNum;

    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Submission submission;

    @Builder
    public SlicingImage(String imageUrl, int problemNum, String answer) {
        this.imageUrl = imageUrl;
        this.problemNum = problemNum;
        this.answer = answer;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }
}
