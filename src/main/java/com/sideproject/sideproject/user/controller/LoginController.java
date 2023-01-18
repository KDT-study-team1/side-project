package com.sideproject.sideproject.user.controller;

import com.sideproject.sideproject.global.response.ResponseDTO;
import com.sideproject.sideproject.user.dto.LoginRequestDTO;
import com.sideproject.sideproject.user.dto.UserResponseDTO;
import com.sideproject.sideproject.user.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Tag(name = "login", description = "로그인 API")
public class LoginController {

    private final LoginService loginService;

    @Operation(summary = "로그인")
    @PostMapping("login")
    public ResponseDTO<?> login(@RequestBody @Valid final LoginRequestDTO loginRequestDTO){
        UserResponseDTO userResponseDTO = loginService.login(loginRequestDTO);
        System.out.println("controller : "+userResponseDTO.toString());
        return new ResponseDTO<>(userResponseDTO);
    }

}
