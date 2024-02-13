package Chaeda_spring.domain.image.domain;

import Chaeda_spring.global.exception.CustomException;
import Chaeda_spring.global.exception.ErrorCode;
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
                .orElseThrow(() -> new CustomException(ErrorCode.IMAGE_FILE_EXTENSION_NOT_FOUND));
    }
}
