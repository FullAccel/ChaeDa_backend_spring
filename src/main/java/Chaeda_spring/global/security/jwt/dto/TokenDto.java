package Chaeda_spring.global.security.jwt.dto;

import lombok.Builder;

@Builder
public record TokenDto(
        String accessTokenDto,
        String refreshTokenDto
) {
    public static TokenDto of(String accessTokenDto, String refreshTokenDto) {
        return TokenDto.builder()
                .accessTokenDto(accessTokenDto)
                .refreshTokenDto(refreshTokenDto)
                .build();
    }
}
