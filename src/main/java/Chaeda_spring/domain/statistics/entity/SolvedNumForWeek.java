package Chaeda_spring.domain.statistics.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private LocalDate startOfWeek;

    @Builder
    public SolvedNumForWeek(LocalDate startOfWeek) {
        this.solvedNum = 1;
        this.startOfWeek = startOfWeek;
    }

    public void increaseSolvedNum(int solvedNum) {
        solvedNum += solvedNum;
    }
}
