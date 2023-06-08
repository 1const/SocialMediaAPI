package com.akmal.socialmediaapi.util.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RegistrationRequest {

    @Schema(description = "Имя пользователя")
    private String username;

    @Schema(description = "Почта пользователя")
    private String mail;

    @Schema(description = "Пароль пользователя")
    private String password;
}
