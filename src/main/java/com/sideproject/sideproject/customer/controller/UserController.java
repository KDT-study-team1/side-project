package com.sideproject.sideproject.customer.controller;

import com.sideproject.sideproject.customer.dto.UserRequestDTO;
import com.sideproject.sideproject.customer.dto.UserResponseDTO;
import com.sideproject.sideproject.customer.service.UserServiceImpl;
import com.sideproject.sideproject.global.response.ResponseDTO;

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

@Controller
@RequiredArgsConstructor
@Tag(name = "user", description = "사용자 API")
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("users")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
            })
    @Operation(summary = "회원 가입", description = "회원가입 api")
    public ResponseDTO<?> signup(@RequestBody final UserRequestDTO userRequestDTO){
        UserResponseDTO userResponseDTO = userService.signup(userRequestDTO);

        return new ResponseDTO<>(200, "success", "회원 가입 성공", userResponseDTO);
    }
}
