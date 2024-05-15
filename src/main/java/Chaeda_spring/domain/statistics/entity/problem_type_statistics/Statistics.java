package Chaeda_spring.domain.statistics.entity.problem_type_statistics;

import Chaeda_spring.domain.Problem.math.MathProblemType;
import Chaeda_spring.global.constant.DifficultyLevel;

public interface Statistics {
    void increaseSolvedNum();

    void increaseDifficultyNumByType(DifficultyLevel difficultyLevel);

    void increaseWrongNum();

    int getSolvedNum();

    int getWrongNum();

    int getEasyNum();

    int getMiddleNum();

    int getHardNum();

    MathProblemType getType();

}
