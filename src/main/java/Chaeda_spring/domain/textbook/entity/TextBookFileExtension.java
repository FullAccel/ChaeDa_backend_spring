package Chaeda_spring.domain.textbook.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TextBookFileExtension {

    pdf("pdf"),
    word("word"),
    hw("hw");

    private final String value;
}
