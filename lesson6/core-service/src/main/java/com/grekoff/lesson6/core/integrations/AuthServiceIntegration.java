package com.grekoff.lesson6.core.integrations;

import com.grekoff.lesson6.api.CartDto;
import com.grekoff.lesson6.api.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class AuthServiceIntegration {

    private final WebClient authServiceWebClient;

    public UserDto getCurrentUserInfo(String username) {
        return authServiceWebClient.get()
//                .uri("http://localhost:8187/lesson6-auth/api/v1/profile/get")
                .uri("/api/v1/profile/get")
                .header("username", username)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }
}
