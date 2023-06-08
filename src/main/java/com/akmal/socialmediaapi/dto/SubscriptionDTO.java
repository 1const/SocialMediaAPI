package com.akmal.socialmediaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SubscriptionDTO {

    @Schema(description = "Является ли подписчик другом для публикатора")
    private boolean isFriend;

    @Schema(description = "Подписчик")
    private UserDTO subscriber;

    @Schema(description = "Публикатор")
    private UserDTO publisher;
}
