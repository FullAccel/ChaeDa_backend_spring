package Chaeda_spring.domain.notification.dto;

import Chaeda_spring.domain.notification.entity.HomeworkNotification;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class HwNotificationRequestDto {

    @Schema(description = "공지 본문 내용", example = "500자를 넘으면 에러납니다", type = "string")
    private String content;

    private int startPage;

    private int endPage;

    @Schema(description = "기간 시작일", example = "2023-11-26 00:00", type = "string")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime deadLine;

    private Long textBookId;

    public HomeworkNotification toEntity(){
        return HomeworkNotification.builder()
                .content(this.content)
                .startPage(this.startPage)
                .endPage(this.endPage)
                .deadLineDateTime(this.deadLine)
                .deadLineDate(this.deadLine.toLocalDate())
                .build();
    }
}
