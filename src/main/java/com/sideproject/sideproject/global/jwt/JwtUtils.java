package com.sideproject.sideproject.global.jwt;

import com.sideproject.sideproject.user.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import org.springframework.data.util.Pair;

import java.security.Key;
import java.util.Date;

/**
 * 토큰 생성하고 검증하는 객체
 */
public class JwtUtils {

    /**
     * 토큰에서 userEmail 추출
     * Token -> clamis
     *
     * @param token : 토큰
     * @return user email
     */
    public static String getUserEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKeyResolver(SigningKeyResolver.instance)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * User의 email로 토큰 생성
     * Header : alg, kid
     * Payload : sub, iat, exp
     * Signature : JwtKey.getRandomKey로 구한 Secret Key를 HS512 암호화
     *
     * @param user : 사용자 객체
     * @return Jwt Token
     */
    public static String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail()); //sub
        Date now = new Date();
        Pair<String, Key> key = JwtKey.getRandomKey();

        //Jwt 토큰 생성
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + JwtProperties.EXPIRATION_TIME)) //토큰 만료시간
                .setHeaderParam(JwsHeader.KEY_ID, key.getFirst())
                .signWith(key.getSecond())
                .compact();
    }

}
