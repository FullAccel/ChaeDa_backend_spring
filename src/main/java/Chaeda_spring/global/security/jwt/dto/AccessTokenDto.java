package Chaeda_spring.global.security.jwt.dto;

import Chaeda_spring.domain.member.entity.Role;

public record AccessTokenDto(
        Long memberId,
        Role role,
        String tokenValue
) {
}
