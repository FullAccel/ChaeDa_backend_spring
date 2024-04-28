package Chaeda_spring.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginRequest(
        @Schema(description = "아이디", example = "test@gmail.com")
        String loginId,
        @Schema(description = "비밀번호", example = "asdf1234!")
        String password
) {
}
