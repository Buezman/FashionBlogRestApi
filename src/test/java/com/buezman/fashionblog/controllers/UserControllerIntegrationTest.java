package com.buezman.fashionblog.controllers;

import com.buezman.fashionblog.dto.UserDto;
import com.buezman.fashionblog.models.User;
import com.buezman.fashionblog.models.enums.Gender;
import com.buezman.fashionblog.models.enums.Role;
import com.buezman.fashionblog.services.implementations.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private CategoryServiceImpl categoryService;

    @MockBean
    private CommentServiceImpl commentService;

    @MockBean
    private LikeServiceImpl likeService;

    @MockBean
    PostServiceImpl postService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void viewAllUsers() {
    }

    @Test
    void shouldCreateAdmin() throws Exception {
        User user = new User(2L, "James", "jamie@gmail.com", "james", Role.ADMIN, Gender.MALE);
        UserDto userDto = new UserDto(2L, "James", "jamie@gmail.com", Gender.MALE, Role.ADMIN);
        when(userService.createAdmin(user)).thenReturn(ResponseEntity.ok(userDto));
        mockMvc.perform(post("http://localhost:8080/api/v1/users/create-admin")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk());
    }

    @Test
    void createCustomer() {
    }

    @Test
    void getUserById() {
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