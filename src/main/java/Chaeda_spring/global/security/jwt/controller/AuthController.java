package Chaeda_spring.global.security.jwt.controller;


import Chaeda_spring.global.security.jwt.dto.TokenDto;
import Chaeda_spring.global.security.jwt.service.JwtTokenService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Auth")
public class AuthController {

    private final JwtTokenService jwtTokenService;

    @PostMapping("/token/reissue")
    @Operation(summary = "AT RT 재발급")
    public ResponseEntity<TokenDto> reIssue(
            @RequestBody TokenDto tokenDto
    ) {
        return ResponseEntity.ok(jwtTokenService.recreateTokenDto(tokenDto.refreshToken()));
    }
}
