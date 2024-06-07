package Chaeda_spring.domain.review_note.folder_and_pdf.entity;

import Chaeda_spring.domain.BaseTimeEntity;
import Chaeda_spring.domain.member.entity.Student;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class ReviewNoteFolder extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Student student;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "image_id")
    private Long imageId;

    @Column(name = "description", length = 500)
    private String description;

    @OneToMany(mappedBy = "reviewNoteFolder", fetch = FetchType.LAZY)
    private List<ReviewNoteProblemFolder> reviewNoteProblemFolders;

    @Builder
    public ReviewNoteFolder(Student student, String title, Long imageId, String description, List<ReviewNoteProblemFolder> reviewNoteProblemFolders) {
        this.student = student;
        this.title = title;
        this.imageId = imageId;
        this.description = description;
        this.reviewNoteProblemFolders = reviewNoteProblemFolders;
    }

    public void mappingProblemsToFolder(List<ReviewNoteProblemFolder> reviewNoteProblemFolders) {
        this.reviewNoteProblemFolders = reviewNoteProblemFolders;
    }
}

