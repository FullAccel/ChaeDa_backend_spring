package Chaeda_spring.domain.announcement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record HwAnnouncementRequest(
        @Schema(description = "숙제 공지 제목, 100자를 넘으면 에러납니다", example = "숙제 공지 제목")
        String title,
        @Schema(description = "공지 본문 내용, 1000자를 넘으면 에러납니다", example = "공지 본문 내용", type = "string")
        String content,
        @Schema(description = "숙제 시작 페이지", example = "50", type = "int")
        int startPage,
        @Schema(description = "숙제 마지막 페이지. 교재의 마지막 페이지보다 클 수 없습니다", example = "56", type = "int")
        int endPage,
        @Schema(description = "숙제 마감 날짜 및 시간", example = "2023-11-26 00:00", type = "string")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        LocalDateTime deadLine,
        @Schema(example = "1", type = "long")
        Long textBookId,
        @Schema(example = "1", type = "long")
        Long classGroupId
) {
}
