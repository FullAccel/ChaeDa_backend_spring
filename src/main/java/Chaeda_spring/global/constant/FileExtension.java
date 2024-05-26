package Chaeda_spring.global.constant;

import Chaeda_spring.global.exception.ErrorCode;
import Chaeda_spring.global.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum FileExtension {
    JPEG("jpeg"),
    JPG("jpg"),
    PNG("png"),
    PDF("pdf"),
    ;

    private final String uploadExtension;

    public static FileExtension from(String uploadExtension) {
        return Arrays.stream(values())
                .filter(
                        imageFileExtension ->
                                imageFileExtension.uploadExtension.equals(uploadExtension))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(ErrorCode.IMAGE_FILE_EXTENSION_NOT_FOUND));
    }
}
