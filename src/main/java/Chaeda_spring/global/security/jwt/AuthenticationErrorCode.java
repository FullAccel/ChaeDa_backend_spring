package Chaeda_spring.global.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthenticationErrorCode {
    UNKNOWN_ERROR(HttpStatus.UNAUTHORIZED, "Unknown", "알 수 없는 에러"),
    EXPIRED_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "Expired", "만료된 access Token입니다"),
    EXPIRED_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "Expired", "만료된 refresh Token입니다. 재로그인하세요"),
    UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED, "Unsupported", "토큰 길이 및 형식이 다른 Token입니다"),
    WRONG_TYPE_TOKEN(HttpStatus.UNAUTHORIZED, "WrongType", "서명이 잘못된 토큰입니다."),
    ACCESS_DENIED(HttpStatus.UNAUTHORIZED, "AccessDenied", "토큰이 없습니다"),
    AT_EXPIRED_AND_RT_NOT_FOUND(HttpStatus.UNAUTHORIZED, "NotFound", "AT는 만료되었고 RT는 비어있습니다.");

    private final HttpStatus status;
    private final String code;

    private final String message;
}
