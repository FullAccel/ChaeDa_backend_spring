package Chaeda_spring.domain.submission.homework.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SubmissionImageRequestDto {

    List<String> image_urls;
}
