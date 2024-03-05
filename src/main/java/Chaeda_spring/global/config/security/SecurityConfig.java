//package Chaeda_spring.global.config.security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//@Configuration
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private final CustomOAuth2UserService customOAuth2UserService;
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http.authorizeHttpRequests(
//                authorize ->
//                        authorize
//                                .requestMatchers("/auth/register")
//                                .authenticated() // 소셜 로그인 임시 토큰으로 인증
//                                .requestMatchers("/auth/**")
//                                .permitAll() // 임시 회원가입 / 로그인 + OAuth2 로그인
//                                .requestMatchers("/v1/**")
//                                .permitAll() // 임시로 모든 요청 허용
//                                .requestMatchers("/oauth2/**")
//                                .permitAll()
//                                .anyRequest()
//                                // TODO: 임시로 모든 url 허용했지만, OIDC에서 권한따라 authentication 할 수 있도록 변경 필요
//                                .authenticated());
//
//        http.exceptionHandling(
//                exception ->
//                        exception.authenticationEntryPoint(
//                                (request, response, authException) -> response.setStatus(401)));
//        return http.build();
//    }
//
//}