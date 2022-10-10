package com.buezman.fashionblog.services;

import com.buezman.fashionblog.dto.LoginDto;
import com.buezman.fashionblog.dto.UserDto;
import com.buezman.fashionblog.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<UserDto> createAdmin(User user);
    ResponseEntity<UserDto> createCustomer(User user);
    List<UserDto> viewAllUsers();
    ResponseEntity<UserDto> findUserById(Long id);
    ResponseEntity<UserDto> editUserDetails(Long id, User user);
    User userLogin(LoginDto loginDto);
}
