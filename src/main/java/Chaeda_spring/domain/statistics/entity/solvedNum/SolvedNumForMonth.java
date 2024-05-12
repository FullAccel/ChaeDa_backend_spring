package Chaeda_spring.domain.statistics.entity.solvedNum;

import Chaeda_spring.domain.member.entity.Student;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class SolvedNumForMonth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int solvedNum;

    @Temporal(TemporalType.DATE)
    private LocalDate monthDate;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Builder
    public SolvedNumForMonth(LocalDate monthDate, Student student) {
        this.solvedNum = 1;
        this.monthDate = monthDate;
        this.student = student;
    }

    public void increaseSolvedNum(int solvedNum) {
        this.solvedNum += solvedNum;
    }
}
