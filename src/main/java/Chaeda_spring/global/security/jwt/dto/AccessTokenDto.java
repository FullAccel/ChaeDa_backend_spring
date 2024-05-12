package Chaeda_spring.global.security.jwt.dto;

import Chaeda_spring.global.constant.Role;

public record AccessTokenDto(
        Long memberId,
        Role role,
        String tokenValue
) {
}
