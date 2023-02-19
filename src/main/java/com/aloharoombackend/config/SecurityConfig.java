package com.aloharoombackend.config;

import com.aloharoombackend.handler.CustomAuthenticationFailureHandler;
import com.aloharoombackend.handler.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //시큐리티 CORS 문제 해결
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));

        corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        corsConfiguration.setAllowedOriginPatterns(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PUT", "OPTIONS", "PATCH"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(Arrays.asList("Authorization"));

        http.csrf().disable(); //csrf 비활성화
        http.authorizeRequests()
                .antMatchers("/**").permitAll() //모든 경로 허락
                .anyRequest().authenticated() //이외 모든 경로는 인증을 받아야 접근 가능
                .and().cors().configurationSource(request -> corsConfiguration) //시큐리티 CORS 문제 해결
                .and()
                .formLogin() //form login 기능 작동
                .loginPage("/loginForm") //사용자 정의 로그인 페이지
                .loginProcessingUrl("/login") //POST로 로그인 정보를 보낼 시 경로 => /login 호출 시 시큐리티 동작하여 로그인
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler);
        return http.build();
    }
}
