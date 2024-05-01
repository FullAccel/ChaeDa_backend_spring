package Chaeda_spring.global.constant;

import Chaeda_spring.global.exception.ErrorCode;
import Chaeda_spring.global.exception.NotFoundException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Grade {

    N_SU("N수"),
    HIGH_1("고1"),
    HIGH_2("고2"),
    HIGH_3("고3"),
    MIDDLE_1("중3"),
    MIDDLE_2("중2"),
    MIDDLE_3("중1"),
    ELEMENT_1("초1"),
    ELEMENT_2("초2"),
    ELEMENT_3("초3"),
    ELEMENT_4("초4"),
    ELEMENT_5("초5"),
    ELEMENT_6("초6");


    private final String value;

    @JsonCreator
    public static Grade fromValue(String value) {
        for (Grade grade : values()) {
            if (grade.toValue().equals(value)) {
                return grade;
            }
        }
        throw new NotFoundException(ErrorCode.UNSUPPORTED_VALUE, value + "는 " + Grade.class.getName() + "에서는 지원하지 않는 타입입니다.");
    }

    @JsonValue
    public String toValue() {
        return this.value;
    }
}
