package com.sideproject.sideproject.global.jwt;

import com.sideproject.sideproject.user.domain.User;
import com.sideproject.sideproject.user.repository.UserRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;

import java.security.Key;
import java.util.Date;

/**
 * 토큰 생성하고 검증하는 객체
 */
@Slf4j
public class JwtUtils {

    private static Pair<String, Key> keyPair = JwtKey.getRandomKey(); // key,value 쌍으로 저장됨

    /**
     * User의 email로 토큰 생성
     */
    public static String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail()); //sub : payload에 저장되는 정보 단위
        Date now = new Date();

        String token = Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(new Date()) //토큰 발행 시간
                .setExpiration(new Date(now.getTime() + JwtProperties.EXPIRATION_TIME)) //토큰 만료시간
                .setHeaderParam(JwsHeader.KEY_ID, keyPair.getFirst()) //헤더에 kid 넣기
                .signWith(keyPair.getSecond(), SignatureAlgorithm.HS512) //알고리즘
                .compact();

        log.info("createAuthToken: {}", token);

        return token;
    }

    /**
     * 토큰에서 userEmail 추출
     */
    public static String getUserEmail(String token) {
        String email = Jwts.parserBuilder()
                .setSigningKeyResolver(SigningKeyResolver.instance) //kid를 찾아서 시그니처 검증
                .build()
                .parseClaimsJws(token) //claims 파싱, 실패시 SignatureException 발생
                .getBody() // subject 찾기
                .getSubject();

        log.info("get email for token : {}", email);

        return email;
    }

}
