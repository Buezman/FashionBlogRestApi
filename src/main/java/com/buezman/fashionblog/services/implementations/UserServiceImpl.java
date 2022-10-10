package com.buezman.fashionblog.services.implementations;

import com.buezman.fashionblog.dto.LoginDto;
import com.buezman.fashionblog.dto.UserDto;
import com.buezman.fashionblog.exception.ResourceNotFoundException;
import com.buezman.fashionblog.models.User;
import com.buezman.fashionblog.models.enums.Role;
import com.buezman.fashionblog.repositories.UserRepository;
import com.buezman.fashionblog.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public ResponseEntity<UserDto> createAdmin(User user) {
        user.setRole(Role.ADMIN);
        UserDto userDto = getUserDto(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> createCustomer(User user) {
        user.setRole(Role.CUSTOMER);
        return ResponseEntity.ok(getUserDto(user));
    }

    private UserDto getUserDto(User user) {
        User newUser = userRepo.save(user);

        UserDto userDto = new UserDto();
        userDto.setRole(newUser.getRole());
        userDto.setId(newUser.getId());
        userDto.setName(newUser.getName());
        userDto.setEmail(newUser.getEmail());
        userDto.setGender(newUser.getGender());

        return userDto;
    }

    @Override
    public List<UserDto> viewAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDto> list = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = new UserDto();

            userDto.setRole(user.getRole());
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setEmail(user.getEmail());
            userDto.setGender(user.getGender());

            list.add(userDto);
        }
        return list;
    }

    @Override
    public ResponseEntity<UserDto> findUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User with id " + id + " not found"));
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setGender(user.getGender());
        userDto.setRole(user.getRole());
        return ResponseEntity.ok(userDto);
    }

    @Override
    public ResponseEntity<UserDto> editUserDetails(Long id, User user) {
       User userToUpdate = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("user with id " + id + " not found"));
       userToUpdate.setName(user.getName());
       userToUpdate.setEmail(user.getName());
       userToUpdate.setPassword(user.getPassword());

       userRepo.save(userToUpdate);

       UserDto userDto = new UserDto();
       userDto.setName(user.getName());
       userDto.setEmail(user.getName());
       userDto.setId(user.getId());
       userDto.setGender(user.getGender());
       userDto.setRole(user.getRole());

       return ResponseEntity.ok(userDto);
    }

    @Override
    public User userLogin(LoginDto loginDto) {
        String email = loginDto.getEmail();
        String password = loginDto.getPassword();
        User user = userRepo.findUserByEmailAndPassword(email, password);
        if (user == null)
            throw new ResourceNotFoundException("invalid login credentials");
        else
            return user;
    }
}
