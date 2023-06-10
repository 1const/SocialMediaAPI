package com.akmal.socialmediaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthenticationDTO {

    @Schema(description = "имя")
    @NotBlank(message = "name doesn't exist")
    private String username;

    @Schema(description = "пароль")
    @NotBlank(message = "password doesn't exist")
    private String password;
}
