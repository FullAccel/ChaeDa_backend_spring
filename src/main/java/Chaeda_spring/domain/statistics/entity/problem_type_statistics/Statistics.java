package Chaeda_spring.domain.statistics.entity.problem_type_statistics;

import Chaeda_spring.global.constant.DifficultyLevel;

public interface Statistics {
    void increaseSolvedNum();

    void increaseDifficultyNumByType(DifficultyLevel difficultyLevel);

    void increaseWrongNum();
}
