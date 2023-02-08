package com.sideproject.sideproject.user.controller;

import com.sideproject.sideproject.global.CookieProvider;
import com.sideproject.sideproject.global.jwt.authToken.AuthToken;
import com.sideproject.sideproject.global.jwt.authToken.AuthTokenProvider;
import com.sideproject.sideproject.global.response.ResponseDTO;
import com.sideproject.sideproject.user.dto.LoginRequestDTO;
import com.sideproject.sideproject.user.dto.UserResponseDTO;
import com.sideproject.sideproject.user.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Tag(name = "login", description = "로그인 API")
public class LoginController {

    private final LoginService loginService;
    private final AuthTokenProvider authTokenProvider;
    private final CookieProvider cookieProvider;

    @Operation(summary = "로그인")
    @PostMapping("login")
    public ResponseDTO<?> login(@RequestBody @Valid final LoginRequestDTO loginRequestDTO){
        UserResponseDTO userResponseDTO = loginService.login(loginRequestDTO);

        AuthToken AT = authTokenProvider.issueAccessToken(userResponseDTO);
        AuthToken RT = authTokenProvider.issueRefreshToken(userResponseDTO);
        loginService.updateRefresh(userResponseDTO, RT);
        ResponseCookie RTcookie = cookieProvider.createRefreshTokenCookie(RT.getToken());
        return new ResponseDTO<>(AT.getToken());

//        JwtUtils jwtUtils = new JwtUtils(userResponseDTO.getEmail());
//
//        String token = jwtUtils.getToken();

//        String token = "token";


//        JwtUtils jwtUtils = new JwtUtils();
//        String token = jwtUtils.getToken();
//        System.out.println(token);
//        if(token.equals(""))
//            throw new UserException(UserExceptionType.THERE_ARE_NO_TOKEN_ERROR);
//        return new ResponseDTO<>(token);
    }

}
