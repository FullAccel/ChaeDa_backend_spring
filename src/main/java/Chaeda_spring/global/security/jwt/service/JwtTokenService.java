package Chaeda_spring.global.security.jwt.service;

import Chaeda_spring.global.constant.Role;
import Chaeda_spring.global.security.jwt.AuthenticationErrorCode;
import Chaeda_spring.global.security.jwt.dto.AccessTokenDto;
import Chaeda_spring.global.security.jwt.dto.TokenDto;
import Chaeda_spring.global.security.jwt.entity.RefreshToken;
import Chaeda_spring.global.security.jwt.entity.RefreshTokenRepository;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

import static Chaeda_spring.global.security.SecurityConstants.TOKEN_ROLE_NAME;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenService {
    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    /**
     * 클라이언트가 로그인 시 전달할 AT, RT를 생성합니다.
     *
     * @param memberId jwt token에 sub로 등록될 memberId입니다.
     * @param role     jwt role에 등록된 권한입니다.
     * @return {@link TokenDto}
     */
    public TokenDto createTokenDto(Long memberId, Role role) {
        Claims claims = Jwts.claims().setSubject(memberId.toString());
        claims.put(TOKEN_ROLE_NAME, role.getKey());

        String accessToken = jwtUtil.generateAccessToken(memberId, role);
        String refreshToken = jwtUtil.generateRefreshToken(memberId, role);

        refreshTokenRepository.save(
                RefreshToken.builder()
                        .memberId(memberId)
                        .token(refreshToken)
                        .build()
        );

        return TokenDto.of(accessToken, refreshToken);
    }

    /**
     * 주어진 액세스 토큰을 검증합니다.
     *
     * @param token 검증할 액세스 토큰.
     * @return 토큰이 유효하면 true, 그렇지 않으면 false.
     * @throws ExpiredJwtException   액세스 토큰이 만료된 경우 발생합니다.
     * @throws JwtException          서명이 잘못된 경우, 토큰이 올바르지 않은 경우, 또는 기타 알 수 없는 오류가 발생한 경우 발생합니다.
     * @throws NumberFormatException 토큰에서 사용자 ID를 파싱하는 데 실패한 경우 발생합니다.
     */
    public boolean validateAccessToken(String token) {
        try {
            Jws<Claims> claims = jwtUtil.getAccessTokenClaims(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            log.error("Access token 만료: " + token);
            throw e;
        } catch (SignatureException e) {
            log.info("SignatureException", e);
            throw new JwtException(AuthenticationErrorCode.WRONG_TYPE_TOKEN.getMessage());
        } catch (MalformedJwtException e) {
            log.info("MalformedJwtException", e);
            throw new JwtException(AuthenticationErrorCode.UNSUPPORTED_TOKEN.getMessage());
        } catch (NumberFormatException e) {
            log.error("Failed to parse user ID from token: " + token, e);
            throw e; // 숫자 변환 실패 처리
        } catch (IllegalArgumentException e) {
            log.info("IllegalArgumentException");
            throw new JwtException(AuthenticationErrorCode.UNKNOWN_ERROR.getMessage());
        }
    }

    /**
     * 주어진 리프레시 토큰을 검증합니다.
     *
     * @param token 검증할 리프레시 토큰.
     * @return 토큰이 유효하면 true, 그렇지 않으면 false.
     * @throws JwtException          리프레시 토큰이 만료된 경우, 서명이 잘못된 경우, 토큰이 올바르지 않은 경우, 또는 기타 알 수 없는 오류가 발생한 경우 발생합니다.
     * @throws NumberFormatException 토큰에서 사용자 ID를 파싱하는 데 실패한 경우 발생합니다.
     */
    public boolean validateRefreshToken(String token) {
        try {
            Jws<Claims> claims = jwtUtil.getRefreshTokenClaims(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            log.error("Refresh token 만료: " + token);
            throw new JwtException(AuthenticationErrorCode.EXPIRED_REFRESH_TOKEN.getMessage());
        } catch (SignatureException e) {
            log.info("SignatureException", e);
            throw new JwtException(AuthenticationErrorCode.WRONG_TYPE_TOKEN.getMessage());
        } catch (MalformedJwtException e) {
            log.info("MalformedJwtException", e);
            throw new JwtException(AuthenticationErrorCode.UNSUPPORTED_TOKEN.getMessage());
        } catch (NumberFormatException e) {
            log.error("Failed to parse user ID from token: " + token, e);
            throw e; // 숫자 변환 실패 처리
        } catch (IllegalArgumentException e) {
            log.info("IllegalArgumentException");
            throw new JwtException(AuthenticationErrorCode.UNKNOWN_ERROR.getMessage());
        }
    }

    /**
     * 해당 RefreshToken이 repository에 존재하는지 확인합니다. 없다면 만료된 토큰입니다.
     *
     * @param refreshToken
     * @return boolean
     */
    public boolean existsRefreshToken(String refreshToken) {
        return refreshTokenRepository.existsRefreshTokenByToken(refreshToken);
    }

    public TokenDto recreateTokenDto(String refreshToken) {
        Jws<Claims> claims = jwtUtil.getRefreshTokenClaims(refreshToken);
        Long memberId = Long.parseLong(claims.getBody().getSubject());
        Role role = Role.findByKey(claims.getBody().get(TOKEN_ROLE_NAME, String.class));
        //기존 refresh Token을 삭제합니다.
        refreshTokenRepository.deleteById(memberId);

        return createTokenDto(memberId, role);
    }

    /**
     * AccessTokenValue에서 data를 parsing하여 AccessTokenDto로 반환합니다.
     *
     * @param accessTokenValue 헤더에서 읽어온 토큰 값입니다.
     * @return {@link AccessTokenDto}
     */
    public AccessTokenDto retrieveAccessToken(String accessTokenValue) {
        return jwtUtil.parseAccessToken(accessTokenValue);
    }

    public void setHeaderAccessToken(HttpServletResponse response, String accessToken) {
        response.setHeader("authorization", accessToken);
    }

    public void setHeaderRefreshToken(HttpServletResponse response, String refreshToken) {
        response.setHeader("refreshToken", refreshToken);
    }

}
