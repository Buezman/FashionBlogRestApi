package com.buezman.fashionblog.controllers;

import com.buezman.fashionblog.dto.UserDto;
import com.buezman.fashionblog.models.User;
import com.buezman.fashionblog.models.enums.Gender;
import com.buezman.fashionblog.models.enums.Role;
import com.buezman.fashionblog.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserControllerUnitTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void viewAllUsers() {
    }

    @Test
    void shouldCreateAdmin() {
        UserService userService = Mockito.mock(UserService.class);
        User user = new User(9L, "Michael", "mich@gmail.com", "michael", Role.ADMIN, Gender.MALE);
        UserDto userDto = new UserDto(9L, "Michael", "mich@gmail.com", Gender.MALE, Role.ADMIN);
        when(userService.createAdmin(user)).thenReturn(ResponseEntity.ok(userDto));
        UserController userController = new UserController(userService);
        assertEquals(ResponseEntity.ok(userDto), userController.createAdmin(user));
    }

    @Test
    void shouldThrowException() {

    }

    @Test
    void createCustomer() {
        UserService userService = Mockito.mock(UserService.class);
        User user = new User(9L, "Michael", "mich@gmail.com", "michael", Role.CUSTOMER, Gender.MALE);
        UserDto userDto = new UserDto(9L, "Michael", "mich@gmail.com", Gender.MALE, Role.CUSTOMER);
        when(userService.createCustomer(user)).thenReturn(ResponseEntity.ok(userDto));
        UserController userController = new UserController(userService);
        assertEquals(ResponseEntity.ok(userDto), userController.createCustomer(user));

    }

    @Test
    void shouldGetUserById() {
        UserService userService = Mockito.mock(UserService.class);

    }

    @Test
    void updateUserDetails() {
    }

    @Test
    void login() {
    }

    @Test
    void logout() {
    }
}