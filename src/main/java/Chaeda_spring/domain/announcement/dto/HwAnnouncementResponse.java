package Chaeda_spring.domain.announcement.dto;

import Chaeda_spring.domain.announcement.entity.HwAnnouncement;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record HwAnnouncementResponse(
        Long id,
        String title,
        int startPage,
        int endPage,
        int submissionNum,
        int classStudentNum,
        String bookImageUrl,
        @Schema(description = "기간 시작일", example = "2023-11-26 00:00", type = "string")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        LocalDateTime deadLine
) {
    public static HwAnnouncementResponse of(HwAnnouncement entity) {
        return HwAnnouncementResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .bookImageUrl(builder().bookImageUrl)
                .classStudentNum(entity.getClassGroup().getCourseList().size())
                .submissionNum(entity.getSubmissionNum())
                .deadLine(entity.getDeadLineDateTime())
                .startPage(entity.getStartPage())
                .endPage(entity.getEndPage())
                .build();
    }
}
