package Chaeda_spring.domain.announcement.dto;

import Chaeda_spring.domain.announcement.entity.HomeworkAnnouncement;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class HwAnnouncementSimpleResponseDto {

    private Long id;

    private String title;

    private int startPage;

    private int endPage;

    private int submissionNum;

    private int classStudentNum;

    private String bookImageUrl;

    @Schema(description = "기간 시작일", example = "2023-11-26 00:00", type = "string")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime deadLine;

    public HwAnnouncementSimpleResponseDto(HomeworkAnnouncement entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.startPage = entity.getStartPage();
        this.endPage = entity.getEndPage();
        this.submissionNum = entity.getSubmissionNum();
        this.classStudentNum = entity.getSubmissionNum();
        this.deadLine = entity.getDeadLineDateTime();
        this.bookImageUrl = entity.getTextbookImageUrl();
    }
}
