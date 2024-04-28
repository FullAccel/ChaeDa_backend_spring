package Chaeda_spring.domain.textbook.entity;

import Chaeda_spring.domain.BaseTimeEntity;
import Chaeda_spring.domain.textbook.dto.UploadTextbookFileRequest;
import Chaeda_spring.global.constant.Grade;
import Chaeda_spring.global.constant.Subject;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Year;

@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
public class Textbook extends BaseTimeEntity {

    @Id
    @Column(name = "TEXTBOOK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int startPageNum;

    @Column(nullable = false)
    private int lastPageNum;

    private String publisher;

    @Column(nullable = false)
    private Year publishYear;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Subject subject;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Grade targetGrade;

    private Long uploadMemberId;

    private String textbookThumbnail;

    private String textbookSrcUrl;

    @Builder
    public Textbook(String name, int startPageNum, int lastPageNum, String publisher, Year publishYear, Subject subject, Grade targetGrade, Long uploadMemberId, String textbookThumbnail, String textbookSrcUrl) {
        this.name = name;
        this.startPageNum = startPageNum;
        this.lastPageNum = lastPageNum;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.subject = subject;
        this.targetGrade = targetGrade;
        this.uploadMemberId = uploadMemberId;
        this.textbookThumbnail = textbookThumbnail;
        this.textbookSrcUrl = textbookSrcUrl;
    }

    public static Textbook from(UploadTextbookFileRequest request) {
        return Textbook.builder()
                .name(request.name())
                .startPageNum(request.startPageNum())
                .lastPageNum(request.lastPageNum())
                .publisher(request.publisher())
                .targetGrade(request.targetGrade())
                .subject(request.subject())
                .publishYear(request.publishYear())
                .build();
    }
}
