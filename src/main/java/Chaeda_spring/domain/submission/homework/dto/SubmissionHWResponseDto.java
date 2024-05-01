package Chaeda_spring.domain.submission.homework.dto;

import Chaeda_spring.domain.submission.homework.entity.homework.SubmissionHomework;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SubmissionHWResponseDto {

    private String announcementName;

    private String bookImageUrl;

    private boolean completeStatus;

    @Schema(description = "기간 마감일", example = "2023-11-26 00:00", type = "string")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime deadLine;

    @Schema(description = "기간 시작일 [주의)2023-11-26 24:00 == 2023-11-27 00:00]", example = "2023-11-26 00:00", type = "string")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;

    private Long dDay;

    @Builder
    public SubmissionHWResponseDto(SubmissionHomework submissionHomework) {
        this.announcementName = submissionHomework.getHwAnnouncement().getTitle();
//        this.bookImageUrl = submission.getHomeworkNotification().getTextbookImageUrl();
        this.completeStatus = submissionHomework.isCompleteStatus();
        this.deadLine = submissionHomework.getHwAnnouncement().getDeadLineDateTime();
        this.createdTime = submissionHomework.getHwAnnouncement().getCreatedDate();
        this.dDay = Duration.between(LocalDateTime.now(), deadLine).toDays();

    }
}
