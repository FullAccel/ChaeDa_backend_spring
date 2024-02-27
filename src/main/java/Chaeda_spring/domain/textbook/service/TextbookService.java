package Chaeda_spring.domain.textbook.service;

import Chaeda_spring.domain.textbook.dto.TextbookResponse;
import Chaeda_spring.domain.textbook.entity.TextbookRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TextbookService {

    private final TextbookRespository textbookRespository;

    public List<TextbookResponse> getTextbookList() {
        return textbookRespository.findAll()
                .stream().map(TextbookResponse::of)
                .collect(Collectors.toList());
    }
}
