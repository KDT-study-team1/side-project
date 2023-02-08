package com.sideproject.sideproject.global;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CookieProvider {

//    private final JwtConfig jwtConfig;

    private String REFRESH_COOKIE = "refreshToken";

    public ResponseCookie createRefreshTokenCookie(String refreshToken){
        deleteRefreshTokenCookie();

        return ResponseCookie.from(REFRESH_COOKIE, refreshToken)
                .httpOnly(true)
                .secure(false) //TODO SSL 인증서 필요해서 나중에
                .sameSite("None")
                .maxAge(100000)
                .path("/refresh")
                .build();
    }

    public void deleteRefreshTokenCookie(){
        ResponseCookie.from(REFRESH_COOKIE, "")
                .maxAge(1)
                .build();
    }
}
