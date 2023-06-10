package com.akmal.socialmediaapi.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class JwtResponse {

    @Schema(description = "JWT токен")
    private String accessToken;

    public JwtResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}