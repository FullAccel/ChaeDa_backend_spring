package Chaeda_spring.domain.member.dto;

import Chaeda_spring.domain.member.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record SignUpRequestForTeacher(
        @NotBlank(message = "로그인 ID는 필수 입력 항목입니다.")
        @Schema(description = "아이디", example = "teacher@gmail.com")
        String loginId,
        @NotBlank()
        @Schema(description = "비밀번호", example = "asdf1234!")
        String password,
        @NotBlank()
        @Schema(description = "이름", example = "홍길동")
        String name,
        @NotBlank()
        @Schema(description = "이메일", example = "teacher@gmail.com")
        String email,
        @NotBlank()
        @Schema(description = "성별", example = "MALE")
        String gender,
        @NotBlank()
        @Schema(description = "전화번호", example = "010-xxxx-xxxx")
        String phoneNumber,

        @NotBlank()
        @Schema(description = "유저타입 (STUDENT or TEACHER)", example = "TEACHER")
        Role role
) implements SignUpRequest {
}
