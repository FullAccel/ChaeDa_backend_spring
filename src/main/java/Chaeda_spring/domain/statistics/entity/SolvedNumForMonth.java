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
public class SolvedNumForMonth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int solvedNum;

    private LocalDate monthDate;

    @Builder
    public SolvedNumForMonth(LocalDate monthDate) {
        this.solvedNum = 1;
        this.monthDate = monthDate;
    }

    public void increaseSolvedNum(int solvedNum) {
        this.solvedNum += solvedNum;
    }
}
