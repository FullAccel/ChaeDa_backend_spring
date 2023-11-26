package Chaeda_spring.domain.class_group.dto;

import Chaeda_spring.domain.class_group.entity.ClassGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClassGroupResponseDto {

    private Long id;

    private String name;

    private String grade;

    private String lessonDays;

    private String profileUrl;

    @Builder
    public ClassGroupResponseDto(ClassGroup classGroup) {
        this.id = classGroup.getId();
        this.name = classGroup.getName();
        this.grade = classGroup.getGrade();
        this.lessonDays = classGroup.getLessonDays();
        this.profileUrl = classGroup.getProfileUrl();
    }
}
