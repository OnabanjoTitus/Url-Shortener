package com.urlshortner.repositoryTest;

import com.urlshortner.data.model.UserEntity;
import com.urlshortner.data.model.UserType;
import com.urlshortner.data.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Transactional
    void testThatACreatedUserExists() {
        //Given
        UserEntity userEntity = new UserEntity();
        userEntity.setUserRole(UserType.USER);
        userEntity.setUserEmail("test@mail.com");
        userEntity.setUserPassword("1234");
        //When
        userRepository.save(userEntity);
        //Assert That
        Assertions.assertTrue(userRepository.existsByUserEmail(userEntity.getUserEmail()));
    }

}
