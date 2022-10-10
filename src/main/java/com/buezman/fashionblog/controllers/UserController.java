package com.buezman.fashionblog.controllers;

import com.buezman.fashionblog.dto.LoginDto;
import com.buezman.fashionblog.dto.UserDto;
import com.buezman.fashionblog.models.User;
import com.buezman.fashionblog.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> viewAllUsers() {
        return userService.viewAllUsers();
    }

    @PostMapping("create-admin")
    public ResponseEntity<UserDto> createAdmin(@RequestBody User user) {
        return userService.createAdmin(user);
    }

    @PostMapping("create-customer")
    public ResponseEntity<UserDto> createCustomer(@RequestBody User user) {
        return userService.createCustomer(user);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUserDetails(@PathVariable Long id, @RequestBody User user) {
        return userService.editUserDetails(id, user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session){
        User user = userService.userLogin(loginDto);
        session.setAttribute("user",user);
        String message = "login successful";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session){
        User user = (User) session.getAttribute("user");
        String message = user.getName()+" has logged out";
        session.invalidate();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
