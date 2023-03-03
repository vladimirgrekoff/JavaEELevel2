package com.grekoff.lesson7.core.integrations;

import com.grekoff.lesson7.api.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class AuthServiceIntegration {

    private final WebClient authServiceWebClient;

    public UserDto getCurrentUserInfo(String username) {
        return authServiceWebClient.get()
//                .uri("http://localhost:8187/lesson7-auth/api/v1/profile/get")
                .uri("/api/v1/profile/get")
                .header("username", username)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }
}
