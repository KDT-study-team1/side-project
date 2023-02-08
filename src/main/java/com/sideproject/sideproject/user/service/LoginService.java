package com.sideproject.sideproject.user.service;

import com.sideproject.sideproject.global.jwt.authToken.AuthToken;
import com.sideproject.sideproject.user.domain.User;
import com.sideproject.sideproject.user.dto.LoginRequestDTO;
import com.sideproject.sideproject.user.dto.UserResponseDTO;

public interface LoginService {

    UserResponseDTO login(LoginRequestDTO loginRequestDTO);

    void updateRefresh(UserResponseDTO user, AuthToken refreshToken);
}
