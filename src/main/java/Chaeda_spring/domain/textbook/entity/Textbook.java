package Chaeda_spring.domain.textbook.entity;

import Chaeda_spring.domain.BaseTimeEntity;
import Chaeda_spring.domain.assignment.entity.SelfAssignment;
import Chaeda_spring.domain.textbook.dto.UploadTextbookFileRequest;
import Chaeda_spring.global.constant.Grade;
import Chaeda_spring.global.constant.Subject;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Year;
import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
public class Textbook extends BaseTimeEntity {

    @Id
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

    @OneToMany(mappedBy = "textbook")
    private List<SelfAssignment> selfAssignments;

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

    /**
     * 교과서의 시작 페이지 번호와 끝 페이지 번호를 기준으로 지정된 페이지 범위가 유효한지 확인합니다.
     *
     * @param startAssignment 과제의 시작 페이지 번호입니다.
     * @param endAssignment   과제의 종료 페이지 번호입니다.
     * @return {@code true}  페이지 범위가 유효한 경우, {@code false} 아닌 경우.
     */
    public boolean isValidPageRange(int startAssignment, int endAssignment) {
        return startAssignment >= this.startPageNum && endAssignment <= this.lastPageNum && startAssignment <= endAssignment;
    }
}
