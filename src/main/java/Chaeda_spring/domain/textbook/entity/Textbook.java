package Chaeda_spring.domain.textbook.entity;

import Chaeda_spring.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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
    private String imageUrl;

    @Column(nullable = false)
    private int lastPageNum;

    @Column(nullable = false)
    private String targetGrade;

    @Builder
    public Textbook(String name, String imageUrl, int lastPageNum, String targetGrade) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.lastPageNum = lastPageNum;
        this.targetGrade = targetGrade;
    }
}
