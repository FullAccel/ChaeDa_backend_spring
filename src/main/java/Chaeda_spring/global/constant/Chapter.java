package Chaeda_spring.global.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Chapter {

    //수학 상
    Polynomial("다항식", Arrays.asList(SubConcept.Operations_of_polynomials, SubConcept.Remainder_theorem_and_factorization)),
    Equations("방정식", Arrays.asList(SubConcept.Complex_numbers, SubConcept.Quadratic_equations, SubConcept.Quadratic_equations_and_quadratic_functions, SubConcept.Various_types_of_equations)),
    Inequalities("부등식", Arrays.asList(SubConcept.Linear_inequalities, SubConcept.Quadratic_inequalities)),
    Equations_of_Shapes("도형의 방정식", Arrays.asList(SubConcept.Plane_coordinates, SubConcept.Equations_of_lines, SubConcept.Equations_of_circles, SubConcept.Transformation_of_shapes)),

    //수학 하
    Sets_and_Propositions("집합과 명제", Arrays.asList(SubConcept.Meaning_and_representation_of_sets, SubConcept.Operations_of_sets, SubConcept.Propositions)),
    Functions("함수", Arrays.asList(SubConcept.Functions, SubConcept.Rational_expressions_and_rational_functions, SubConcept.Irrational_expressions_and_irrational_functions)),
    Permutations_and_Combinations("순열과 조합", List.of(SubConcept.Permutations_and_combinations)),

    //수학 1
    Exponential_and_Logarithmic_Functions("지수함수와 로그함수", Arrays.asList(SubConcept.Exponents_and_Logarithms, SubConcept.Exponential_and_Logarithmic_Functions)),
    Trigonometric_Functions("삼각함수", Arrays.asList(SubConcept.Trigonometric_Functions, SubConcept.Graphs_of_Trigonometric_Functions, SubConcept.Applications_of_Trigonometric_Functions)),
    Sequences("수열", Arrays.asList(SubConcept.Arithmetic_and_Geometric_Sequences, SubConcept.Sum_of_Sequences, SubConcept.Mathematical_Induction)),

    //수학 2
    Functions_Limits_and_Continuity("함수의 극한과 연속", Arrays.asList(SubConcept.Functions_Limits, SubConcept.Functions_Continuity)),
    Differentiation("미분", Arrays.asList(SubConcept.Differential_Coefficient, SubConcept.Derivative_Functions, SubConcept.Applications_of_Derivatives)),
    Integration_in_Math_2("적분", Arrays.asList(SubConcept.Indefinite_Integrals, SubConcept.Definite_Integrals, SubConcept.Applications_of_Definite_Integrals_in_Math_2)),

    //미적분
    Limits_of_Sequences("수열의 극한", Arrays.asList(SubConcept.Limits_of_Sequences, SubConcept.Series)),
    Differential_Calculus("미분법", Arrays.asList(SubConcept.Differentiation_of_Various_Functions, SubConcept.Various_Differentiation_Methods, SubConcept.Applications_of_Derivative_Functions)),
    Integration_in_calculus("적분법", Arrays.asList(SubConcept.Various_Integration_Methods, SubConcept.Applications_of_Definite_Integrals_in_Calculus)),

    //확률과통계
    Counting_Methods("경우의 수", Arrays.asList(SubConcept.Permutations_and_Combinations, SubConcept.Binomial_Theorem)),
    Probability("확률", Arrays.asList(SubConcept.Probability, SubConcept.Conditional_Probability)),
    Statistics("통계", Arrays.asList(SubConcept.Probability_Distributions, SubConcept.Statistical_Estimation)),

    //기하
    Conic_Sections("이차곡선", Arrays.asList(SubConcept.Conic_Sections, SubConcept.Conic_Sections_and_Lines)),
    Plane_Vectors("평면벡터", Arrays.asList(SubConcept.Vector_Operations, SubConcept.Components_and_Dot_Product_of_Plane_Vectors)),
    Spatial_Shapes_and_Coordinates("공간도형과 공간좌표", Arrays.asList(SubConcept.Spatial_Shapes, SubConcept.Spatial_Coordinates));

    private final String value;
    private final List<SubConcept> subConcepts;

    public static List<SubConcept> getSubConceptsByChapterName(Chapter target) {
        return Arrays.stream(Chapter.values())
                .filter(chapter -> chapter.value.equals(target.getValue()))
                .findFirst()
                .map(Chapter::getSubConcepts)
                .orElseThrow(() -> new IllegalArgumentException("Chapter not found: " + target.getValue()));
    }
}
