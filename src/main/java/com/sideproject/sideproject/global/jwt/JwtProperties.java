package com.sideproject.sideproject.global.jwt;

import org.springframework.beans.factory.annotation.Value;

/**
 * JWT 기본 설정값
 */
public class JwtProperties {

    /**
     * 토큰 만료 시간
     */
    public static final int EXPIRATION_TIME = 600000; // 10분
    @Value("${jwt.cookieName}")
    public static String COOKIE_NAME;

    /**
     * Secret key (외부 유출 XXXXX)
     */
    @Value("${jwt.secretKey-1}")
    public static String SECRET_KEY_1;
    @Value("${jwt.secretKey-1}")
    public static String SECRET_KEY_2;
    @Value("${jwt.secretKey-1}")
    public static String SECRET_KEY_3;
}
