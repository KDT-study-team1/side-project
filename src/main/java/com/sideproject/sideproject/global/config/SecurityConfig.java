package com.sideproject.sideproject.global.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        String[] matchers = {"/", "/login/**", "/users", "/refresh", "/swagger-ui/**", "/api-docs/**"};

        //login
        http
                .cors()//기본 cors 설정
                .and()
                .csrf().disable() //csrf 비활성화
                .formLogin().disable() //formLogin 인증 비활성화
                .httpBasic().disable() //httpBasic 인증 비활성화
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers(matchers).permitAll() //모두에게 허용할 URL
                .anyRequest().authenticated(); //인증이 되었는지 검증

        //logout
        http
                .logout().permitAll()
                .logoutUrl("/logout");

        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
