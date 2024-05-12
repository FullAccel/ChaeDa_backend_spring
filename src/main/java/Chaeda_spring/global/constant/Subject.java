package Chaeda_spring.global.constant;

import Chaeda_spring.global.exception.ErrorCode;
import Chaeda_spring.global.exception.NotFoundException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Subject {

    Math_high("수학 상"),
    Math_low("수학 하"),
    Math_1("수학1"),
    Math_2("수학2"),
    Calculus("미적분"),
    Probability_and_Statistics("확률과 통계"),
    Geometry("기하"),
    Mix("혼합형");

    private final String value;

    @JsonCreator
    public static Subject fromValue(String value) {
        for (Subject subject : values()) {
            if (subject.getValue().equals(value)) {
                return subject;
            }
        }
        throw new NotFoundException(ErrorCode.UNSUPPORTED_VALUE, value + "는 " + Subject.class.getName() + "에서는 지원하지 않는 타입입니다.");
    }

    @JsonValue
    public String toValue() {
        return this.value;
    }
}
