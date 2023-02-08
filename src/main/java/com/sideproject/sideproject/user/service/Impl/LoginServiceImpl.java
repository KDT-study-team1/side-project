package com.sideproject.sideproject.user.service.Impl;

import com.sideproject.sideproject.global.jwt.authToken.AuthToken;
import com.sideproject.sideproject.global.jwt.authToken.AuthTokenProvider;
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
    private final AuthTokenProvider authTokenProvider;

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

    @Override
    public void updateRefresh(UserResponseDTO user, AuthToken refreshToken) {
//        loginUser.updateRefreshToken(refreshToken.getToken()); //DB의 RT 갈아끼우기
    }

    public AuthToken refresh(Long userId, AuthToken refreshToken) {
        //DB에 있는 RT랑 비교
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserException(UserExceptionType.NON_EXISTENT_USER));
        UserResponseDTO userResponseDTO = new UserResponseDTO(user);
//        if(!refreshToken.getToken().equals(user.getRefreshToken())) throw new UnAuthorizedException(UnAuthorizedExceptionType.REFRESH_TOKEN_UN_AUTHORIZED);
        //RT가 유효하므로 AT 재발급
        return authTokenProvider.issueAccessToken(userResponseDTO);
    }
}
