package com.core.backend.user.application;

import com.core.backend.common.exception.ErrorCode;
import com.core.backend.user.application.dto.UserSignUpServiceRequest;
import com.core.backend.user.domain.PasswordEncryptor;
import com.core.backend.user.domain.User;
import com.core.backend.user.domain.repository.UserRepository;
import com.core.backend.user.exception.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserCommandService {

    private final UserRepository userRepository;
    private final PasswordEncryptor passwordEncoder;

    public void signUp(UserSignUpServiceRequest request) {

        validateDuplicateEmail(request.getEmail());
        validateDuplicatePhoneNumber(request.getPhoneNumber());

        User user = request.toEntity()
                .encode(request.getPassword(), passwordEncoder);

        userRepository.save(user);
    }

    private void validateDuplicateEmail(String email) {
        if(userRepository.existsByEmail(email)) {
            throw new UserException(ErrorCode.DUPLICATE_EMAIL);
        }
    }

    private void validateDuplicatePhoneNumber(String phoneNumber) {
        if(userRepository.existsByEmail(phoneNumber)) {
            throw new UserException(ErrorCode.DUPLICATE_PHONE_NUMBER);
        }
    }
}