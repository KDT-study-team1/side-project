package com.sideproject.sideproject.user.service;

import com.sideproject.sideproject.user.dto.LoginRequestDTO;
import com.sideproject.sideproject.user.dto.UserResponseDTO;

public interface LoginService {

    UserResponseDTO login(LoginRequestDTO loginRequestDTO);
}
