package Chaeda_spring.global.constant;

import Chaeda_spring.global.exception.ErrorCode;
import Chaeda_spring.global.exception.NotFoundException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum DifficultyLevel {
    LOW("하"),
    MEDIUM("중"),
    HIGH("상");

    private final String value;

    DifficultyLevel(String value) {
        this.value = value;
    }

    @JsonCreator
    public static DifficultyLevel fromValue(String value) {
        for (DifficultyLevel difficultyLevel : values()) {
            if (difficultyLevel.getValue().equals(value)) {
                return difficultyLevel;
            }
        }
        throw new NotFoundException(ErrorCode.UNSUPPORTED_VALUE, value + "는 " + DifficultyLevel.class.getName() + "에서는 지원하지 않는 타입입니다.");
    }

    @JsonValue
    public String toValue() {
        return this.value;
    }
}
