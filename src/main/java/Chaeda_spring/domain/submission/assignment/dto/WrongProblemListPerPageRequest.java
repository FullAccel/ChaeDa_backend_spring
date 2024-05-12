package Chaeda_spring.domain.submission.assignment.dto;

import Chaeda_spring.global.constant.DifficultyLevel;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;

public record WrongProblemListPerPageRequest(
        @Schema(description = "페이지 번호", example = "12")
        int pageNumber,
        @Schema(description = "틀린 문제 기록, '문제번호': '난이도'", example = "{'51': '하', '56': '중', '58': '상'}", implementation = HashMap.class)
        HashMap<String, DifficultyLevel> wrongProblemRecords
) {
}
