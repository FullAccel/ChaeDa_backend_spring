package Chaeda_spring.global.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Chapter {

    //수학 상
    Polynomial("다항식"),
    Equations("방정식"),
    Inequalities("부등식"),
    Equations_of_Shapes("도형의 방정식"),

    //수학 하
    Sets_and_Propositions("집합과 명제"),
    Functions("함수"),
    Permutations_and_Combinations("순열과 조합"),

    //수학1
    Exponential_and_Logarithmic_Functions("지수함수와 로그함수"),
    Trigonometric_Functions("삼각함수"),
    Sequences("수열"),

    //수학2
    Functions_Limits_and_Continuity("함수의 극한과 연속"),
    Differentiation("미분"),
    Integration_in_Math_2("적분"),

    //미적분
    Limits_of_Sequences("수열의 극한"),
    Differential_Calculus("미분법"),
    Integration_in_calculus("적분법"),

    //확률과 통계
    Counting_Methods("경우의 수"),
    Probability("확률"),
    Statistics("통계"),

    //기하
    Conic_Sections("이차곡선"),
    Plane_Vectors("평면벡터"),
    Spatial_Shapes_and_Coordinates("공간도형과 공간좌표");
    private final String value;
}
