package Chaeda_spring.domain.assignment.entity;

import Chaeda_spring.domain.assignment.dto.SelfAssignmentRequest;
import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.domain.textbook.entity.Textbook;
import Chaeda_spring.global.exception.CustomException;
import Chaeda_spring.global.exception.ErrorCode;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static Chaeda_spring.global.constant.ValidationConstant.TITLE_LENGTH_MAX;

@Entity
@Getter
@NoArgsConstructor
public class SelfAssignment {

    @Size(min = 1, max = TITLE_LENGTH_MAX)
    @Column(nullable = false)
    private String title;
    private int startPage;
    private int endPage;

    @Temporal(TemporalType.DATE)
    private LocalDate targetDate;
    @OneToOne
    private Textbook textbook;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder
    public SelfAssignment(String title, int startPage, int endPage, LocalDate targetDate, Textbook textbook, Student student) {
        this.title = title;
        this.startPage = startPage;
        this.endPage = endPage;
        this.targetDate = targetDate;
        this.textbook = textbook;
        this.student = student;
    }

    public static SelfAssignment createSelfAssignment(SelfAssignmentRequest request, Textbook textbook, Student student) {
        if (!textbook.isValidPageRange(request.startPage(), request.endPage())) {
            throw new CustomException(ErrorCode.INCORRECT_SCOPE, "교재 페이지 범위를 벗어났거나 올바르지 못한 범위입니다.");
        }
        return SelfAssignment.builder()
                .title(request.title())
                .startPage(request.startPage())
                .endPage(request.endPage())
                .textbook(textbook)
                .targetDate(request.targetDate())
                .student(student)
                .build();
    }


    public void updateSelfAssignment(SelfAssignmentRequest request, Textbook textbook) {
        if (!textbook.isValidPageRange(request.startPage(), request.endPage())) {
            throw new CustomException(ErrorCode.INCORRECT_SCOPE, "교재 페이지 범위를  벗어났거나 올바르지 못한 범위입니다.");
        }
        this.title = request.title();
        this.startPage = request.startPage();
        this.endPage = request.endPage();
        this.targetDate = request.targetDate();
        this.textbook = textbook;
    }
}
