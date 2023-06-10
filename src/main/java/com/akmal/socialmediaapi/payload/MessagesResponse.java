package com.akmal.socialmediaapi.payload;

import com.akmal.socialmediaapi.dto.MessageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MessagesResponse {

    @Schema(description = "Диалог с пользователем")
    private List<MessageDTO> messages;

}
