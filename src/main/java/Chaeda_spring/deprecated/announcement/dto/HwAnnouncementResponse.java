package Chaeda_spring.deprecated.announcement.dto;

import Chaeda_spring.deprecated.announcement.entity.HwAnnouncement;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record HwAnnouncementResponse(
        @Schema(example = "1")
        Long id,
        @Schema(example = "숙제 공지 제목")
        String title,
        @Schema(example = "50")
        int startPage,
        @Schema(example = "56")
        int endPage,
        @Schema(example = "5")
        int submissionNum,
        @Schema(example = "10")
        int classStudentNum,
        @Schema(example = "https://s3-fullaccel.s3.ap-northeast-2.amazonaws.com/textbook_profile/%E1%84%8A%E1%85%A6%E1%86%AB_%E1%84%89%E1%85%AE%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%A1%E1%86%BC_2024.jpeg")
        String bookImageUrl,
        @Schema(description = "마감일", example = "2024-02-26 00:00", type = "string")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        LocalDateTime deadLine,

        @Schema(description = "공지 생성일", example = "2024-02-24 00:00", type = "string")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        LocalDateTime createDateTime
) {
    public static HwAnnouncementResponse of(HwAnnouncement entity) {
        return HwAnnouncementResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .bookImageUrl(builder().bookImageUrl)
                .classStudentNum(entity.getClassGroup().getCourseList().size())
                .submissionNum(entity.getSubmissionNum())
                .deadLine(entity.getDeadLineDateTime())
                .createDateTime(entity.getCreatedDate())
                .startPage(entity.getStartPage())
                .endPage(entity.getEndPage())
                .build();
    }
}
