package com.sideproject.sideproject.user.service.Impl;

import com.sideproject.sideproject.user.domain.User;
import com.sideproject.sideproject.user.dto.LoginRequestDTO;
import com.sideproject.sideproject.user.dto.UserResponseDTO;
import com.sideproject.sideproject.user.exception.UserException;
import com.sideproject.sideproject.user.exception.UserExceptionType;
import com.sideproject.sideproject.user.repository.UserRepository;
import com.sideproject.sideproject.user.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO login(LoginRequestDTO loginRequestDTO) {
        User user = userRepository
                .findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(()-> new UserException(UserExceptionType.NON_EXISTENT_USER));

        if(passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword()))
            return new UserResponseDTO(user);
        else
            throw new UserException(UserExceptionType.UNMATCHED_PASSWORD);

    }
}
