package Chaeda_spring.domain.File.entity;

import Chaeda_spring.domain.member.entity.Member;
import Chaeda_spring.global.constant.FileExtension;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private String title;

    private String fileSrcName;

    @Enumerated(EnumType.STRING)
    private FileExtension fileExtension;

    //    @Column(columnDefinition= "TIMESTAMP")
    private LocalDateTime createdDateTime;

    @Builder
    public File(Member member, String title, String fileSrcName, FileExtension fileExtension, LocalDateTime createdDateTime) {
        this.member = member;
        this.title = title;
        this.fileSrcName = fileSrcName;
        this.fileExtension = fileExtension;
        this.createdDateTime = createdDateTime;
    }
}
