package com.urlshortner.serviceTest;

import com.urlshortner.data.model.Status;
import com.urlshortner.service.UserService;
import com.urlshortner.web.dtos.RegisterRequestDto;
import com.urlshortner.web.dtos.RegisterResponseDto;
import com.urlshortner.web.exceptions.UserServiceException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Transactional
    void testThatUserRegistrationDTOCannotBeNull() {
        //Given
        RegisterRequestDto registerRequestDto = null;
        //Assert That
        Assertions.assertThrows(UserServiceException.class, () -> userService.registerUser(registerRequestDto));
    }

    @Test
    @Transactional
    void testThatUserRegistrationDTOFieldsCannotBeNull() {
        //Given
        RegisterRequestDto registerRequestDto = new RegisterRequestDto();
        //Assert That
        Assertions.assertThrows(UserServiceException.class, () -> userService.registerUser(registerRequestDto));
    }

    @Test
    @Transactional
    void testThatUserRegistrationCanBeDone() {
        //Given
        RegisterRequestDto registerRequestDto = new RegisterRequestDto();
        registerRequestDto.setUserEmail("test@mail.com");
        registerRequestDto.setUserPassword("1234");
        registerRequestDto.setConfirmPassword("1234");
        //Assert That
        RegisterResponseDto registerResponseDto = userService.registerUser(registerRequestDto);
        Assertions.assertEquals(Status.SUCCESS, registerResponseDto.getStatus());
    }

    @Test
    @Transactional
    void testThatUserCannotRegisterTwice() {
        //Given
        RegisterRequestDto registerRequestDto = new RegisterRequestDto();
        registerRequestDto.setUserEmail("test@mail.com");
        registerRequestDto.setUserPassword("1234");
        registerRequestDto.setConfirmPassword("1234");
        //Assert That
        RegisterResponseDto registerResponseDto = userService.registerUser(registerRequestDto);
        Assertions.assertEquals(Status.SUCCESS, registerResponseDto.getStatus());
        registerResponseDto = userService.registerUser(registerRequestDto);
        Assertions.assertEquals(Status.ALREADY_REGISTERED, registerResponseDto.getStatus());
    }

}
