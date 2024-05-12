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
public class SolvedNumForDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int solvedNum;

    @Temporal(TemporalType.DATE)
    private LocalDate todayDate;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Builder
    public SolvedNumForDay(LocalDate todayDate, Student student) {
        this.solvedNum = 0;
        this.todayDate = todayDate;
        this.student = student;
    }

    public void increaseSolvedNum(int increaseNum) {
        solvedNum += increaseNum;
    }
}
