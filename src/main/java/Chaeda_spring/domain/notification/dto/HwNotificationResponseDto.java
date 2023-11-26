package Chaeda_spring.domain.notification.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class HwNotificationResponseDto {

    private String content;

    private int startPage;

    private int endPage;

    private int submissionNum;

    private int classStudentNum;

    @Schema(description = "기간 시작일", example = "2023-11-26 00:00", type = "string")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime deadLine;
}
