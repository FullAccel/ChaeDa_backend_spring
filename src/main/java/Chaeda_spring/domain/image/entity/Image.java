package Chaeda_spring.domain.image.entity;

import Chaeda_spring.domain.BaseTimeEntity;
import Chaeda_spring.domain.submission.homework.entity.homework.SubmissionHomework;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ImageType imageType;

    private Long memberId;

    @Column(length = 36)
    private String imageKey;

    @Enumerated(EnumType.STRING)
    private ImageFileExtension imageFileExtension;

    @ManyToOne(fetch = FetchType.LAZY)
    private SubmissionHomework submissionHomework;

    @Builder
    public Image(
            ImageType imageType,
            Long memberId,
            String imageKey,
            ImageFileExtension imageFileExtension) {
        this.imageType = imageType;
        this.memberId = memberId;
        this.imageKey = imageKey;
        this.imageFileExtension = imageFileExtension;
    }

    public void setSubmissionHomework(SubmissionHomework submissionHomework) {
        this.submissionHomework = submissionHomework;
    }

    public void updateImageKey(String imageKey) {
        this.imageKey = imageKey;
    }
}
