package com.ecommerce.userservice.service;

import java.util.UUID;

import com.ecommerce.userservice.dto.SignupDto;
import com.ecommerce.userservice.entity.User;

public interface UserService {

    User getUserById(UUID userId);

    User getUserByEmail(String email);

    User createNewUser(SignupDto signupDto);

}
