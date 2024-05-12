package Chaeda_spring.domain.statistics;

import Chaeda_spring.domain.statistics.entity.solvedNum.SolvedNumForDay;
import Chaeda_spring.global.utils.MemberUtils;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubconceptListener {

    private final MemberUtils memberUtils;

    @PreUpdate
    @PrePersist
    public void updateSolvedNum(SolvedNumForDay solvedNumForDay) {


    }
}
