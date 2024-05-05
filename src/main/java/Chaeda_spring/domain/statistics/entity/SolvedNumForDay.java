package Chaeda_spring.domain.statistics.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private LocalDate todayDate;

    @Builder
    public SolvedNumForDay(LocalDate todayDate) {
        this.solvedNum = 0;
        this.todayDate = todayDate;
    }

    public void increaseSolvedNum(int increaseNum) {
        solvedNum += increaseNum;
    }
}
