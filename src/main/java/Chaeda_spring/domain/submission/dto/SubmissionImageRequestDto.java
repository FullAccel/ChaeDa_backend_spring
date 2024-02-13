package Chaeda_spring.domain.submission.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SubmissionImageRequestDto {

    List<String> image_urls;
}
