package Chaeda_spring.global.security.jwt.service;

import Chaeda_spring.global.constant.Role;
import Chaeda_spring.global.security.jwt.dto.AccessTokenDto;
import Chaeda_spring.global.security.jwt.dto.RefreshTokenDto;
import Chaeda_spring.global.security.jwt.entity.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import static Chaeda_spring.global.security.SecurityConstants.TOKEN_ROLE_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {


    private final JwtProperties jwtProperties;

    /**
     * AccessToken을 생성합니다.
     *
     * @param memberId jwt token에 sub로 등록될 memberId입니다.
     * @param role     jwt role에 등록된 권한입니다.
     * @return AccessToken 문자열
     */
    public String generateAccessToken(Long memberId, Role role) {
        Date issuedAt = new Date();
        Date expiredAt = new Date(issuedAt.getTime() + jwtProperties.accessTokenExpirationMilliTime());
        return Jwts.builder()
                .setIssuer(jwtProperties.issuer())
                .setSubject(memberId.toString())
                .claim(TOKEN_ROLE_NAME, role.getKey())
                .setIssuedAt(issuedAt)
                .setExpiration(expiredAt)
                .signWith(getAccessTokenKey())
                .compact();
    }

    /**
     * RefreshToken을 생성합니다.
     *
     * @param memberId jwt token에 sub로 등록될 memberId입니다.
     * @param role     jwt role에 등록된 권한입니다.
     * @return RefreshToken 문자열
     */
    public String generateRefreshToken(Long memberId, Role role) {
        Date issuedAt = new Date();
        Date expiredAt = new Date(getRefreshTokenExpiredAt());

        return Jwts.builder()
                .setIssuer(jwtProperties.issuer())
                .setSubject(memberId.toString())
                .claim(TOKEN_ROLE_NAME, role.getKey())
                .setIssuedAt(issuedAt)
                .setExpiration(expiredAt)
                .signWith(getRefreshTokenKey())
                .compact();
    }

    public long getRefreshTokenExpiredAt() {
        Date issuedAt = new Date();
        return issuedAt.getTime() + jwtProperties.refreshTokenExpirationMilliTime();
    }

    /**
     * 토큰 파싱하여 성공하면 AccessTokenDto 반환, 실패하면 null 반환
     *
     * @param token
     * @return
     * @throws ExpiredJwtException 만료된 토큰인 경우
     */
    public AccessTokenDto parseAccessToken(String token) throws ExpiredJwtException {
        Jws<Claims> claims = getAccessTokenClaims(token);
        return new AccessTokenDto(
                Long.parseLong(claims.getBody().getSubject()),
                Role.findByKey(claims.getBody().get(TOKEN_ROLE_NAME, String.class)),
                token
        );
    }


    public RefreshTokenDto parseRefreshToken(String token) throws ExpiredJwtException {
        Jws<Claims> claims = getRefreshTokenClaims(token);
        return new RefreshTokenDto(
                Long.parseLong(claims.getBody().getSubject()),
                token);
    }

    public Jws<Claims> getAccessTokenClaims(String token) {
        return Jwts.parserBuilder()
                .requireIssuer(jwtProperties.issuer())
                .setSigningKey(getAccessTokenKey())
                .build()
                .parseClaimsJws(token);
    }

    public Jws<Claims> getRefreshTokenClaims(String token) {
        return Jwts.parserBuilder()
                .requireIssuer(jwtProperties.issuer())
                .setSigningKey(getRefreshTokenKey())
                .build()
                .parseClaimsJws(token);
    }

    private Key getAccessTokenKey() {
        byte[] keyBytes = jwtProperties.accessTokenSecret().getBytes();
        // SHA-256 알고리즘에 적합한 키를 생성합니다.
//        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Key getRefreshTokenKey() {
        return Keys.hmacShaKeyFor(jwtProperties.refreshTokenSecret().getBytes());
    }
}
