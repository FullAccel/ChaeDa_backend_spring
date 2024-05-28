package Chaeda_spring.global.security.jwt;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        try {
            filterChain.doFilter(request, response);
        } catch (JwtException ex) {
            String message = ex.getMessage();
            logger.error(ex);
            if (AuthenticationErrorCode.UNKNOWN_ERROR.getMessage().equals(message)) {
                setResponse(response, AuthenticationErrorCode.UNKNOWN_ERROR);
            }
            //잘못된 타입의 토큰인 경우
            else if (AuthenticationErrorCode.WRONG_TYPE_TOKEN.getMessage().equals(message)) {
                setResponse(response, AuthenticationErrorCode.WRONG_TYPE_TOKEN);
            }
            //Access 토큰 만료된 경우
            else if (AuthenticationErrorCode.EXPIRED_ACCESS_TOKEN.getMessage().equals(message)) {
                setResponse(response, AuthenticationErrorCode.EXPIRED_ACCESS_TOKEN);
            }
            //Refresh 토큰 만료된 경우
            else if (AuthenticationErrorCode.EXPIRED_REFRESH_TOKEN.getMessage().equals(message)) {
                setResponse(response, AuthenticationErrorCode.EXPIRED_REFRESH_TOKEN);
            }
            //지원되지 않는 토큰인 경우
            else if (AuthenticationErrorCode.UNSUPPORTED_TOKEN.getMessage().equals(message)) {
                setResponse(response, AuthenticationErrorCode.UNSUPPORTED_TOKEN);
            } else {
                setResponse(response, AuthenticationErrorCode.ACCESS_DENIED);
            }
        }
    }

    private void setResponse(HttpServletResponse response, AuthenticationErrorCode errorCode) throws RuntimeException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorCode.getStatus().value());
        response.getWriter().print(errorCode.getMessage());
    }
}
