package com.grekoff.lesson7.auth.controllers;


import com.grekoff.lesson7.auth.exceptions.AppError;
import com.grekoff.lesson7.auth.services.UsersService;
import com.grekoff.lesson7.auth.utils.JwtTokenUtil;
import com.grekoff.lesson7.api.JwtRequest;
import com.grekoff.lesson7.api.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/authenticate")
//@CrossOrigin("*")
public class AuthController {

    private final UsersService usersService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.toString(), "Имя пользователя или пароль не найдены"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = usersService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

}
