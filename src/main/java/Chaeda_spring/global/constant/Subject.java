package Chaeda_spring.global.constant;

import Chaeda_spring.global.exception.ErrorCode;
import Chaeda_spring.global.exception.NotFoundException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Subject {

    Math_high("수학 상", Arrays.asList(Chapter.Polynomial, Chapter.Equations, Chapter.Inequalities, Chapter.Equations_of_Shapes)),
    Math_low("수학 하", Arrays.asList(Chapter.Sets_and_Propositions, Chapter.Functions, Chapter.Permutations_and_Combinations)),
    Math_1("수학1", Arrays.asList(Chapter.Exponential_and_Logarithmic_Functions, Chapter.Trigonometric_Functions, Chapter.Sequences)),
    Math_2("수학2", Arrays.asList(Chapter.Functions_Limits_and_Continuity, Chapter.Differentiation, Chapter.Integration_in_Math_2)),
    Calculus("미적분", Arrays.asList(Chapter.Limits_of_Sequences, Chapter.Differential_Calculus, Chapter.Integration_in_calculus)),
    Probability_and_Statistics("확률과 통계", Arrays.asList(Chapter.Counting_Methods, Chapter.Probability, Chapter.Statistics)),
    Geometry("기하", Arrays.asList(Chapter.Conic_Sections, Chapter.Plane_Vectors, Chapter.Spatial_Shapes_and_Coordinates)),
    Mix("혼합형", List.of());


    private final String value;
    private final List<Chapter> chapters;


    @JsonCreator
    public static Subject fromValue(String value) {
        for (Subject subject : values()) {
            if (subject.getValue().equals(value)) {
                return subject;
            }
        }
        throw new NotFoundException(ErrorCode.UNSUPPORTED_VALUE, value + "는 " + Subject.class.getName() + "에서는 지원하지 않는 타입입니다.");
    }

    public static List<Chapter> getChaptersBySubjectName(Subject target) {
        return Arrays.stream(Subject.values())
                .filter(subject -> subject.value.equals(target.getValue()))
                .findFirst()
                .map(Subject::getChapters)
                .orElseThrow(() -> new IllegalArgumentException("Subject not found: " + target.getValue()));
    }

    @JsonValue
    public String toValue() {
        return this.value;
    }
}
