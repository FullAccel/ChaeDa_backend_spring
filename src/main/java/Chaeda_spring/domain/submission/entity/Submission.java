package Chaeda_spring.domain.submission.entity;

import Chaeda_spring.domain.BaseTimeEntity;
import Chaeda_spring.domain.announcement.entity.HwAnnouncement;
import Chaeda_spring.domain.member.entity.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
public class Submission extends BaseTimeEntity {

    @Id
    @Column(name = "SUBMISSION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //완료여부
    private boolean completeStatus = false;

    private long dDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hw_notification_id")
    private HwAnnouncement hwAnnouncement;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @OneToMany(mappedBy = "submission")
    private List<SubmissionImage> submissionImageList = new ArrayList<>();

    @OneToMany(mappedBy = "submission")
    private List<SlicingImage> slicingImageList = new ArrayList<>();

    public void setHwAnnouncement(HwAnnouncement hwAnnouncement) {
        this.hwAnnouncement = hwAnnouncement;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
