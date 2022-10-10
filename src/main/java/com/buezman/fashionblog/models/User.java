package com.buezman.fashionblog.models;

import com.buezman.fashionblog.models.enums.Gender;
import com.buezman.fashionblog.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank
    private String name;

    @Email
    @Column(nullable = false, unique = true)
    private String email;
    @Size(min = 5)
    private String password;
    private Role role;
    private Gender gender;

}
