package Chaeda_spring.domain.review_note.entity;

import Chaeda_spring.domain.BaseTimeEntity;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.review_note.dto.ReviewNoteProblemInfo;
import Chaeda_spring.global.constant.Chapter;
import Chaeda_spring.global.constant.FileExtension;
import Chaeda_spring.global.constant.ImageType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
public class ReviewNoteProblem extends BaseTimeEntity {

    @Enumerated(EnumType.STRING)
    private ImageType imageType;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Student student;
    @Column(name = "incorrect_date", nullable = false)
    private LocalDate incorrectDate;
    @Column(name = "imageKey", nullable = false)
    private String imageKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "imageFileExtension", nullable = false)
    private FileExtension fileExtension;
    @Column(name = "answer", nullable = false)
    private String answer;

    @Column(nullable = false)
    private String textbookName;

    @Column(nullable = false)
    private String problemNum;
    @Enumerated(EnumType.STRING)
    private Chapter chapter;


    @Builder
    public ReviewNoteProblem(Student student, LocalDate incorrectDate, String imageKey, FileExtension fileExtension, String answer, String textbookName, String problemNum, Chapter chapter) {
        this.student = student;
        this.incorrectDate = incorrectDate;
        this.imageKey = imageKey;
        this.fileExtension = fileExtension;
        this.answer = answer;
        this.textbookName = textbookName;
        this.problemNum = problemNum;
        this.chapter = chapter;
        this.imageType = ImageType.REVIEW_NOTE_PROBLEM;
    }

    public static ReviewNoteProblem of(Student student, ReviewNoteProblemInfo request) {
        return ReviewNoteProblem.builder()
                .student(student)  // Assuming you have studentId in DTO
                .incorrectDate(request.incorrectDate())
                .imageKey(request.imageKey())
                .fileExtension(request.fileExtension())
                .answer(request.answer())
                .textbookName(request.textbookName())
                .problemNum(request.problemNum())
                .chapter(request.chapter())
                .build();
    }
}

