package com.grekoff.lesson3.dtos;

import com.grekoff.lesson3.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    public Long id;
    private String username;
    private String password;
    private String email;
    private Collection<Role> roles;
}
