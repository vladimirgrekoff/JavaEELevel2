package com.grekoff.lesson3.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
//    private Collection<Role> roles;
    private String token;
}
