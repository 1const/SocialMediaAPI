package com.akmal.socialmediaapi.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegistrationRequest {

    @Schema(description = "Имя пользователя")
    @NotBlank(message = "name doesn't exist")
    private String username;

    @Schema(description = "Почта пользователя")
    @Email(message = "incorrect mail")
    @NotBlank(message = "mail doesn't exist")
    private String mail;

    @Schema(description = "Пароль пользователя")
    @NotBlank(message = "password doesn't exist")
    private String password;
}
