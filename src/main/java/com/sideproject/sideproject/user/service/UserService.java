package com.sideproject.sideproject.user.service;

import com.sideproject.sideproject.user.dto.UserRequestDTO;
import com.sideproject.sideproject.user.dto.UserResponseDTO;

public interface UserService {

    //회원가입
    UserResponseDTO signup(UserRequestDTO requestDTO);
//
//    //프로필 편집
//    void editProfile();
//
//    //회원 탈퇴
//    void removeUser();
}
