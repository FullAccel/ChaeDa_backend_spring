package Chaeda_spring.domain.review_note.entity;

import Chaeda_spring.domain.BaseTimeEntity;
import Chaeda_spring.domain.image.entity.ImageFileExtension;
import Chaeda_spring.domain.image.entity.ImageType;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.review_note.dto.ReviewNoteProblemInfo;
import Chaeda_spring.domain.textbook.entity.Textbook;
import Chaeda_spring.global.constant.Chapter;
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
    private ImageFileExtension imageFileExtension;
    @Column(name = "answer", nullable = false)
    private String answer;

    @ManyToOne
    private Textbook textbook;
    @Column(nullable = false)
    private String problemNum;
    @Enumerated(EnumType.STRING)
    private Chapter chapter;


    @Builder
    public ReviewNoteProblem(Student student, LocalDate incorrectDate, String imageKey, ImageFileExtension imageFileExtension, String answer, Textbook textbook, String problemNum, Chapter chapter) {
        this.student = student;
        this.incorrectDate = incorrectDate;
        this.imageKey = imageKey;
        this.imageFileExtension = imageFileExtension;
        this.answer = answer;
        this.textbook = textbook;
        this.problemNum = problemNum;
        this.chapter = chapter;
        this.imageType = ImageType.REVIEW_NOTE_PROBLEM;
    }

    public static ReviewNoteProblem of(Student student, ReviewNoteProblemInfo request, Textbook textbook) {
        return ReviewNoteProblem.builder()
                .student(student)  // Assuming you have studentId in DTO
                .incorrectDate(request.incorrectDate())
                .imageKey(request.imageKey())
                .imageFileExtension(request.imageFileExtension())
                .answer(request.answer())
                .textbook(textbook)
                .problemNum(request.problemNum())
                .chapter(request.chapter())
                .build();
    }
}

