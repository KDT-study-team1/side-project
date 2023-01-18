package com.sideproject.sideproject.user.controller;

import com.sideproject.sideproject.user.dto.UserRequestDTO;
import com.sideproject.sideproject.user.dto.UserResponseDTO;
import com.sideproject.sideproject.global.response.ResponseDTO;

import com.sideproject.sideproject.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "user", description = "사용자 API")
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping("users")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
            })
    @Operation(summary = "회원 가입", description = "회원가입 api")
    public ResponseDTO<?> signup(@RequestBody final UserRequestDTO userRequestDTO){
        UserResponseDTO userResponseDTO = userService.signup(userRequestDTO);

        return new ResponseDTO<>(userResponseDTO);
    }


}
