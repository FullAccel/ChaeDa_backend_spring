package Chaeda_spring.domain.class_group.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Grade {

    N_SU("N_SU"),
    HIGH_1("high_1"),
    HIGH_2("high_2"),
    HIGH_3("high_3"),
    MIDDLE_1("middle_1"),
    MIDDLE_2("middle_2"),
    MIDDLE_3("middle_3"),
    ELEMENT_1("element_1"),
    ELEMENT_2("element_2"),
    ELEMENT_3("element_3"),
    ELEMENT_4("element_4"),
    ELEMENT_5("element_5"),
    ELEMENT_6("element_6");


    private final String value;
}
