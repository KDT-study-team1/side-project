package com.sideproject.sideproject.global.jwt;

import com.sideproject.sideproject.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * 토큰 인증 담당
 *
 * 1. cookie에서 jwt token을 가져온다.
 * 2. token을 파싱해서 user email을 구한다.
 * 3. user email로 User 엔티티를 가져오고 Authentication을 생성한다.
 * 4. 생성된 Authentication을 SecurityContext에 넣는다.
 * 5. Exeption이 발생하면 response의 cookie를 null 변경한다.
 */
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private UserRepository userRepository;

    public JwtAuthorizationFilter(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("JWT filter is running...");

        String token = null;
        try { //쿠키에서 JWT 토큰을 가져오기
            token = Arrays.stream(request.getCookies())
                    .filter(cookie -> cookie.getName().equals(JwtProperties.COOKIE_NAME)) //필요한 쿠키만 가져오기
                    .findFirst()
                    .map(Cookie::getValue) //쿠키 값 가져오기
                    .orElse(null); //못 찾아오면 null
        }catch (Exception ignored){
            //아무것도 안함?
        }

        if(token != null){
            try{
                //athentication을 만들어서 SecurityContext에 넣는다.
                Authentication authentication = this.getUsernamePasswordAuthenticationToken(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }catch (Exception e){
                //실패하면 cookie 초기화 (실패하면 쿠키가 쓸모 없어져서)
                Cookie cookie = new Cookie(JwtProperties.COOKIE_NAME,null);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }

        filterChain.doFilter(request, response);
    }

    /**
     * JWT 토큰으로 User를 찾아서 UsernamePasswordAuthenticationToken으로 만들어서 리턴
     * User가 없으면 null
     */
    private Authentication getUsernamePasswordAuthenticationToken(String token){
        String userEmail = JwtUtils.getUserEmail(token);
        if(userEmail != null){
            return new UsernamePasswordAuthenticationToken(userEmail, null, AuthorityUtils.NO_AUTHORITIES);
        }
        return null;
    }
}
