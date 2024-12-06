package com.example.sensor_api.client;


import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.HashMap;
import java.util.Map;

@Component
public class AuthClient {

    private final WebClient webClient;

    public AuthClient() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080") // URL вашего API
                .build();
    }

    // Метод для получения JWT токена
    public String getJwtToken(String username, String password) {
        try {
            Map<String, String> authRequest = new HashMap<>();
            authRequest.put("username", username);
            authRequest.put("password", password);

            Map<String, String> response = webClient.post()
                    .uri("/login")
                    .bodyValue(authRequest)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            // Предполагается, что в ответе есть поле "token"
            return response != null ? response.get("token") : null;
        } catch (WebClientResponseException e) {
            System.err.println("Error during login: " + e.getMessage());
            return null;
        }
    }
}
