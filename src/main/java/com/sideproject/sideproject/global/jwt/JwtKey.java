package com.sideproject.sideproject.global.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.data.util.Pair;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Map;
import java.util.Random;

/**
 * JWT Key를 제공하고 조회한다.
 * Key rolling
 */
public class JwtKey {

    /**
     * Kid-set list (외부 유출 XXXXX)
     */
    private static final Map<String,String> SECRET_KEY_SET = Map.of(
            "key1",JwtProperties.SECRET_KEY_1,
            "key2",JwtProperties.SECRET_KEY_2,
            "key3",JwtProperties.SECRET_KEY_3
    );


    private static final String[] KID_SET = SECRET_KEY_SET.keySet().toArray(new String[0]);
    private static Random randomIndex = new Random();

    /**
     * SECRET_KEY_SET에서 랜덤한 Key 가져오기
     * @return kid와 key Pair
     */
    public static Pair<String, Key> getRandomKey(){
        String kid = KID_SET[randomIndex.nextInt(KID_SET.length)];
        String secretKey = SECRET_KEY_SET.get(kid);

        // hmacShaKeyFor() : secret key의 길이에 따라서 암호화 방식을 알아서 선택해준다.
        return Pair.of(kid, Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * kid로 key찾기
     *
     * @param kid
     * @return key
     */
    public static Key getKey(String kid){
        String key = SECRET_KEY_SET.getOrDefault(kid, null);
        if (key == null)
            return null;
        return Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
    }


}
