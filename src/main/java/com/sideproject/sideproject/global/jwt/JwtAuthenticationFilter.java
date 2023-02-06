package com.sideproject.sideproject.global.jwt;

import com.sideproject.sideproject.user.domain.User;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * 로그인 담당 및 토큰 발급 진행
 */
@Getter
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
        this.authenticationManager = authenticationManager;
    }

    /**
     * 로그인 시도
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        //로그인할 때 입력한 user email과 password로 authenticationToken을 생성한다.
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                request.getParameter("email"),
                request.getParameter("password"),
                new ArrayList<>()
        );
        log.info("authenticationToken = "+authenticationToken);
        return authenticationManager.authenticate(authenticationToken);
    }

    /**
     * 인증에 성공했을때 호출됨
     * JwtToken을 생성해서 쿠키에 넣는다.
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);

        User user = (User) authResult.getPrincipal();
        String token = JwtUtils.createToken(user);

        Cookie cookie = new Cookie(JwtProperties.COOKIE_NAME, token); //쿠키 생성
        cookie.setMaxAge(JwtProperties.EXPIRATION_TIME); //쿠키 만료 시간
        cookie.setPath("/"); //쿠키를 사용할 수 있는 경로
        response.addCookie(cookie); //response에 쿠키 넣기
        response.sendRedirect("/");
    }

    /**
     * 로그인 실패시 (로그인 페이지로 보내야함)
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);

        response.sendRedirect("/login");
    }
}
