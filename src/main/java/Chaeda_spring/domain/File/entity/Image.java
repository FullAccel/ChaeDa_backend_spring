package Chaeda_spring.domain.File.entity;

import Chaeda_spring.domain.BaseTimeEntity;
import Chaeda_spring.domain.submission.homework.entity.homework.SubmissionHomework;
import Chaeda_spring.global.constant.FileExtension;
import Chaeda_spring.global.constant.ImageType;
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
    private Long id;

    @Enumerated(EnumType.STRING)
    private ImageType imageType;

    private Long memberId;

    @Column(length = 36)
    private String imageKey;

    @Enumerated(EnumType.STRING)
    private FileExtension fileExtension;

    @ManyToOne(fetch = FetchType.LAZY)
    private SubmissionHomework submissionHomework;

    private String filename;

    @Builder
    public Image(
            ImageType imageType,
            Long memberId,
            String imageKey,
            FileExtension fileExtension,
            String filename) {
        this.imageType = imageType;
        this.memberId = memberId;
        this.imageKey = imageKey;
        this.fileExtension = fileExtension;
        this.filename = filename;
    }

    public void setSubmissionHomework(SubmissionHomework submissionHomework) {
        this.submissionHomework = submissionHomework;
    }

    public void updateImageKey(String imageKey) {
        this.imageKey = imageKey;
    }
}
