package com.sideproject.sideproject.global.jwt.authToken;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sideproject.sideproject.global.CustomUserDetails;
import com.sideproject.sideproject.global.jwt.JwtConfig;
import com.sideproject.sideproject.global.jwt.JwtType;
import com.sideproject.sideproject.user.dto.UserResponseDTO;
import com.sideproject.sideproject.user.exception.UserException;
import com.sideproject.sideproject.user.exception.UserExceptionType;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Slf4j
public class AuthTokenProvider {

    @Autowired
    private JwtConfig jwtConfig;

    private Key key;

    public AuthTokenProvider(String secret){
        key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public AuthToken createAuthToken(String id, Date expiry) {
        return new AuthToken(key, id, expiry);
    }

    public AuthToken convertAuthToken(String token) {
        return new AuthToken(token, key);
    }

    public AuthToken issueAccessToken(UserResponseDTO userResponseDTO) {
        String subject = getTokenSubjectStr(userResponseDTO, JwtType.ACCESS);
        Date expiryDate = Date.from(
                Instant.now().plusSeconds(jwtConfig.getAccessExpiry()));

        AuthToken authToken = createAuthToken(subject, expiryDate);
        log.info("issueAccessToken.AuthToken.getToken(): {} ");
        return authToken;
    }

    public AuthToken issueRefreshToken(UserResponseDTO user) {
        String subject = getTokenSubjectStr(user, JwtType.REFRESH);
        Date expiryDate = Date.from(
                Instant.now().plusSeconds(jwtConfig.getRefreshExpiry()));

        AuthToken authToken = createAuthToken(subject, expiryDate);
        log.info("issueRefreshToken.AuthToken.getToken(): {} ");
        return authToken;
    }

    /**
     * ?????? ?????? ??? ???????????? ?????????
     * ?????? ????????? ?????? CustomUserDetails ????????? ???????????? String?????? ????????? ??????
     * @param userResponseDTO
     * @param jwtType
     * @return String
     */
    private String getTokenSubjectStr(UserResponseDTO userResponseDTO, JwtType jwtType) {
        ObjectMapper om = new ObjectMapper();
        try {
            return om.writeValueAsString(new CustomUserDetails(userResponseDTO.getUserId(), userResponseDTO.getEmail(), jwtType));
        } catch (JsonProcessingException e) {
            log.debug(e.getMessage());
            throw new UserException(UserExceptionType.PARSING_FAIL);
        }
    }

    /**
     * jwt ?????? ??? ?????????(?????????) ??????
     * ????????? JwtException ???????????? ????????? ????????? ???????????????, ????????? ???(filter)????????? ???????????? ??????
     * @param authToken
     * @return
     */
    public UsernamePasswordAuthenticationToken getAuthentication(AuthToken authToken) throws JsonProcessingException {
        if(authToken.validate()){
            Claims claims = authToken.getClaimsFromToken(); //?????? ?????? ??????
            CustomUserDetails userDetails = CustomUserDetails.createUserDetails(claims.getSubject());
            return new UsernamePasswordAuthenticationToken(userDetails, null, AuthorityUtils.NO_AUTHORITIES);
        } else {
            return null;
        }
    }

}
