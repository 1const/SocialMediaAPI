package com.akmal.socialmediaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AuthenticationDTO {

    @Schema(description = "имя")
    private String username;

    @Schema(description = "пароль")
    private String password;
}
