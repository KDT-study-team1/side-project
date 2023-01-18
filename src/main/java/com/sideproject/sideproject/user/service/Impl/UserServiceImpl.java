package com.sideproject.sideproject.user.service.Impl;

import com.sideproject.sideproject.user.domain.User;
import com.sideproject.sideproject.user.dto.UserRequestDTO;
import com.sideproject.sideproject.user.dto.UserResponseDTO;
import com.sideproject.sideproject.user.repository.UserRepository;
import com.sideproject.sideproject.user.exception.UserException;
import com.sideproject.sideproject.user.exception.UserExceptionType;
import com.sideproject.sideproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원 가입
     * @param requestDTO : 회원가입시 필요한 데이터 dto
     * @return 저장 완료후 responseDTO 반환
     */
    @Override
    public UserResponseDTO signup(UserRequestDTO requestDTO) {
        String email = requestDTO.getEmail();
        toVerifyDuplicate(email);
        
        User user = requestDTO.toEntity();
        user.encoderPassword(passwordEncoder);

        return new UserResponseDTO(userRepository.save(user));
    }

    /**
     * 이메일 중복 여부 체크
     * @param email : 사용자 이메일
     */
    public void toVerifyDuplicate(String email){
        if(userRepository.findByEmail(email).isPresent()){
            throw new UserException(UserExceptionType.DUPLICATE_EMAIL);
        }
    }
}
