package com.akmal.socialmediaapi.controller;

import com.akmal.socialmediaapi.dto.MessageDTO;
import com.akmal.socialmediaapi.security.UserPrincipal;
import com.akmal.socialmediaapi.service.MessageService;
import com.akmal.socialmediaapi.payload.MessageRequest;
import com.akmal.socialmediaapi.payload.MessagesResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("social-media-api/v1/friends")
@RequiredArgsConstructor
@Tag(name = "Контроллер для работы с сообщениями")
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/{userId}/messages")
    @Operation(description = "Получить диалог по ID пользователя")
    public ResponseEntity<MessagesResponse> findMessagesByUserId(
            @Parameter(description = "ID пользователя, у которого получаем диалог")
            @PathVariable Long userId,

            @Parameter(description = "Текущий пользователь")
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        MessagesResponse messagesResponse =
                messageService.findMessagesByUserId(userId, userPrincipal);

        return ResponseEntity.ok(messagesResponse);
    }

    @PostMapping("/{friendId}/messages")
    @Operation(description = "Отправить сообщение другу")
    public ResponseEntity<MessageDTO> sendMessageToFriend(
            @Parameter(description = "Запрос с текстом сообщения")
            @Valid
            @RequestBody MessageRequest messageRequest,

            @Parameter(description = "ID пользователя, которому отправляем сообщение")
            @PathVariable Long friendId,

            @Parameter(description = "Текущий пользователь")
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        MessageDTO returnedMessage =
                messageService.sendMessageToFriend(friendId, userPrincipal, messageRequest);

        return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
    }
}
