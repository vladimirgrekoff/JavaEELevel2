package com.grekoff.lesson2.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
//    private Collection<Role> roles;
    private String token;
}
