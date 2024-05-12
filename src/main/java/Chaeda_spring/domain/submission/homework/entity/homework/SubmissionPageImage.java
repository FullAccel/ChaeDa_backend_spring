package Chaeda_spring.domain.submission.homework.entity.homework;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class SubmissionPageImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imageUrl;

    private int pageNum;

    @ManyToOne(fetch = FetchType.LAZY)
    private SubmissionHomework submissionHomework;

    @Builder
    public SubmissionPageImage(String imageUrl, int pageNum) {
        this.imageUrl = imageUrl;
        this.pageNum = pageNum;
    }

    public void setSubmissionHomework(SubmissionHomework submissionHomework) {
        this.submissionHomework = submissionHomework;
    }
}
