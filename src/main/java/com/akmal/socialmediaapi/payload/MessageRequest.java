package com.akmal.socialmediaapi.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest {

    @Schema(description = "Текст сообщения")
    @NotBlank
    private String text;
}
