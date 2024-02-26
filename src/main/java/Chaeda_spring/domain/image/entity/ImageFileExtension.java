package Chaeda_spring.domain.image.entity;

import Chaeda_spring.global.exception.ErrorCode;
import Chaeda_spring.global.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ImageFileExtension {
    JPEG("jpeg"),
    JPG("jpg"),
    PNG("png"),
    ;

    private final String uploadExtension;

    public static ImageFileExtension from(String uploadExtension) {
        return Arrays.stream(values())
                .filter(
                        imageFileExtension ->
                                imageFileExtension.uploadExtension.equals(uploadExtension))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(ErrorCode.IMAGE_FILE_EXTENSION_NOT_FOUND));
    }
}
