package com.akmal.socialmediaapi.controller;

import com.akmal.socialmediaapi.dto.SubscriptionDTO;
import com.akmal.socialmediaapi.security.UserPrincipal;
import com.akmal.socialmediaapi.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("social-media-api/v1/subs")
@RequiredArgsConstructor
@Tag(name = "Контроллер для работы с подписками пользователей")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;


    @PostMapping("/{subscriberId}/change-status")
    @Operation(description = "Принять/Отклонить заявку в друзья")
    public ResponseEntity<SubscriptionDTO> changeSubscriptionStatus(
            @Parameter(description = "Текущий пользователь")
            @AuthenticationPrincipal UserPrincipal publisher,

            @Parameter(description = "ID подписчика")
            @PathVariable(name = "subscriberId") Long subscriberId) {
        SubscriptionDTO subscription =
                subscriptionService.changeSubscriptionStatus(publisher, subscriberId);

        return ResponseEntity.ok(subscription);
    }

}
