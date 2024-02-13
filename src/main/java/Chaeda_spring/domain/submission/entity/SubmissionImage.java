package Chaeda_spring.domain.submission.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class SubmissionImage {

    @Id
    @Column(name = "SUBMISSION_IMAGE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imageUrl;

    private int pageNum;

    @ManyToOne(fetch = FetchType.LAZY)
    private Submission submission;

    @Builder
    public SubmissionImage(String imageUrl, int pageNum) {
        this.imageUrl = imageUrl;
        this.pageNum = pageNum;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }
}
