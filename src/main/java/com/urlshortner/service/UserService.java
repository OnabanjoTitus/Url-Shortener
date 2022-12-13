package com.urlshortner.service;

import com.urlshortner.web.dtos.LoginRequestDto;
import com.urlshortner.web.dtos.LoginResponseDto;
import com.urlshortner.web.dtos.RegisterRequestDto;
import com.urlshortner.web.dtos.RegisterResponseDto;

public interface UserService {
    LoginResponseDto loginUser(final LoginRequestDto loginRequestDto);

    RegisterResponseDto registerUser(final RegisterRequestDto registerRequestDto);
}
