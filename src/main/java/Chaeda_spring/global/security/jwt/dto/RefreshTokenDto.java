package Chaeda_spring.global.security.jwt.dto;

public record RefreshTokenDto(
        Long memberId,
        String tokenValue
) {
}
