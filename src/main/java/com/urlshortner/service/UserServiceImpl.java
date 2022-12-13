package com.urlshortner.service;

import com.urlshortner.data.model.Status;
import com.urlshortner.data.model.UserEntity;
import com.urlshortner.data.model.UserType;
import com.urlshortner.data.repository.UserRepository;
import com.urlshortner.web.dtos.LoginRequestDto;
import com.urlshortner.web.dtos.LoginResponseDto;
import com.urlshortner.web.dtos.RegisterRequestDto;
import com.urlshortner.web.dtos.RegisterResponseDto;
import com.urlshortner.web.exceptions.UserServiceException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponseDto loginUser(LoginRequestDto loginRequestDto) {
        //Todo:
        return null;
    }

    @Override
    public RegisterResponseDto registerUser(RegisterRequestDto registerRequestDto) {
        checkCredentialsForRegistration(registerRequestDto);
        boolean userExists = checkIfUserAlreadyExists(registerRequestDto.getUserEmail());
        RegisterResponseDto registerResponseDto = new RegisterResponseDto();
        if (userExists) {
            registerResponseDto.setStatus(Status.ALREADY_REGISTERED);
            return registerResponseDto;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUserEmail(registerRequestDto.getUserEmail());
        userEntity.setUserRole(UserType.USER);
        userEntity.setUserPassword(passwordEncoder.encode(registerRequestDto.getUserPassword()));
        userRepository.save(userEntity);
        registerResponseDto.setStatus(Status.SUCCESS);
        return registerResponseDto;
    }

    private boolean checkIfUserAlreadyExists(String userEmail) {
        return userRepository.existsByUserEmail(userEmail);
    }

    private void checkCredentialsForRegistration(RegisterRequestDto registerRequestDto) {
        if (registerRequestDto == null) {
            throw new UserServiceException("Register Request Cannot Be Null");
        }
        if (StringUtils.isEmpty(registerRequestDto.getUserEmail())) {
            throw new UserServiceException("Email Cannot be empty");
        }
        if (StringUtils.isEmpty(registerRequestDto.getUserPassword())) {
            throw new UserServiceException("Password Cannot Be Empty");
        }
        if (StringUtils.isEmpty(registerRequestDto.getConfirmPassword())) {
            throw new UserServiceException("Confirm Password Cannot Be Empty");
        }
        if (!registerRequestDto.getUserPassword().equals(registerRequestDto.getConfirmPassword())) {
            throw new UserServiceException("Password and Confirm Password Cannot Be Different");
        }
    }
}
