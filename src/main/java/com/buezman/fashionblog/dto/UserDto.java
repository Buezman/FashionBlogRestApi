package com.buezman.fashionblog.dto;

import com.buezman.fashionblog.models.enums.Gender;
import com.buezman.fashionblog.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private Gender gender;
    private Role role;
}
