package com.buezman.fashionblog.services;

import com.buezman.fashionblog.dto.LoginDto;
import com.buezman.fashionblog.dto.UserDto;
import com.buezman.fashionblog.exception.ResourceNotFoundException;
import com.buezman.fashionblog.models.User;
import com.buezman.fashionblog.models.enums.Gender;
import com.buezman.fashionblog.models.enums.Role;
import com.buezman.fashionblog.repositories.UserRepository;
import com.buezman.fashionblog.services.implementations.UserServiceImpl;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceMockitoTests {

    @Mock
    UserRepository userRepository;

    @Mock
    private HttpSession httpSession;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    @Order(1)
    public void test_viewAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "John Doe", "john@gmail.com", "johnD", Role.ADMIN, Gender.MALE));
        users.add(new User(2L, "Jane Doe", "jane@gmail.com", "janeD", Role.CUSTOMER, Gender.FEMALE));

        when(userRepository.findAll()).thenReturn(users);
        assertEquals(2, userService.viewAllUsers().size());
    }

    @Test
    public void test_shouldCreateAdmin() {
        UserService userService = Mockito.mock(UserService.class);
        User user = new User(9L, "Michael", "mich@gmail.com", "michael", Role.ADMIN, Gender.MALE);
        UserDto userDto = new UserDto(9L, "Michael", "mich@gmail.com", Gender.MALE, Role.ADMIN );

        given(userRepository.save(any())).willReturn(user);
        ResponseEntity<UserDto> response = userService.createAdmin(user);
        when(userService.createAdmin(user)).thenReturn(response);
        assertEquals(response, userService.createAdmin(user));
    }

    @Test
    @Order(2)
    public void test_shouldFindUserById() {
        User user = new User(1L, "John Doe", "jdoe@gmail.com", "jdoe", Role.CUSTOMER, Gender.MALE);
        given(userRepository.findById(any())).willReturn(Optional.of(user));
        userService.findUserById(1L);
        verify(userRepository).findById(1L);
    }

    @Test
    public void test_shouldThrowUserNotFoundExceptionWhenIdNotFound() {
        given(userRepository.findById(2L)).willReturn(Optional.empty());
        assertThatThrownBy(() -> userService.findUserById(2L)).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void test_successfulLogin() {
        LoginDto loginDto = new LoginDto("jdoe@gmail.com", "jdoe");
        User user = new User(3L, "John Doe", "jdoe@gmail.com", "jdoe", Role.CUSTOMER, Gender.MALE);

        given(userRepository.findUserByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword())).willReturn(user);
        User response = userService.userLogin(loginDto);

        assertEquals(response.getId(), userService.userLogin(loginDto).getId());
    }

    @Test
    public void test_invalidLoginCredentials() {
        LoginDto loginDto = new LoginDto("jdon@gmail.com", "jdoe");

        given(userRepository.findUserByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword())).willThrow(new ResourceNotFoundException("invalid login credentials"));

        assertThatThrownBy(() -> userService.userLogin(loginDto)).isInstanceOf(ResourceNotFoundException.class);

    }
}
