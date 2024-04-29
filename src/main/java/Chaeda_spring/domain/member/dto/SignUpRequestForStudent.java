package Chaeda_spring.domain.member.dto;

import Chaeda_spring.domain.member.entity.Role;
import Chaeda_spring.global.constant.Grade;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record SignUpRequestForStudent(
        @NotBlank(message = "로그인 ID는 필수 입력 항목입니다.")
        @Schema(description = "아이디", example = "student@gmail.com")
        String loginId,
        @NotBlank()
        @Schema(description = "비밀번호", example = "asdf1234!")
        String password,
        @NotBlank()
        @Schema(description = "이름", example = "홍길동")
        String name,
        @NotBlank()
        @Schema(description = "이메일", example = "student@gmail.com")
        String email,
        @NotBlank()
        @Schema(description = "성별", example = "MALE")
        String gender,
        @NotBlank()
        @Schema(description = "전화번호", example = "010-xxxx-xxxx")
        String phoneNumber,
        @NotBlank(message = "학교이름은 필수 입력 항목이며 비어있을 수 없습니다.")
        @Schema(description = "학교이름 (선생님인 경우 받지 않습니다", example = "건대부속고등학교")
        String schoolName,
        @NotBlank(message = "학년은 필수 입력 항목이며 비어있을 수 없습니다.")
        @Schema(description = "학년 (선생님인 경우 받지 않습니다", example = "고2")
        Grade grade,
        @NotBlank()
        @Schema(description = "유저타입 (STUDENT or TEACHER)", example = "STUDENT")
        Role role
) implements SignUpRequest {
}
