package Chaeda_spring.domain.statistics.entity.solvedNum;

import Chaeda_spring.domain.member.entity.Student;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class SolvedNumForWeek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int solvedNum;

    @Temporal(TemporalType.DATE)
    private LocalDate startOfWeek;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Builder
    public SolvedNumForWeek(LocalDate startOfWeek, Student student) {
        this.startOfWeek = startOfWeek;
        this.student = student;
    }

    public void increaseSolvedNum(int solvedNum) {
        this.solvedNum += solvedNum;
    }
}
