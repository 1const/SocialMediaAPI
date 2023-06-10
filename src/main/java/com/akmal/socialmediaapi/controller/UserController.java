package com.akmal.socialmediaapi.controller;

import com.akmal.socialmediaapi.dto.UserDTO;
import com.akmal.socialmediaapi.security.UserPrincipal;
import com.akmal.socialmediaapi.service.PostService;
import com.akmal.socialmediaapi.service.SubscriptionService;
import com.akmal.socialmediaapi.service.UserService;
import com.akmal.socialmediaapi.payload.PostsByAuthorIdResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/social-media-api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Контроллер для работы с пользователями")
public class UserController {

    private final String DEFAULT_PAGE_NUMBER = "0";
    private final String DEFAULT_PAGE_SIZE = "5";

    private final UserService userService;

    private final PostService postService;

    private final SubscriptionService subscriptionService;


    @GetMapping("/me")
    @Operation(summary = "Получение текущего пользователя")
    public ResponseEntity<UserDTO> getCurrentUser(
            @Parameter(description = "Данные о пользователе")
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        UserDTO userDTO = userService.getCurrentUser(userPrincipal);

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/{authorId}/posts")
    @Operation(summary = "Получение постов пользователя по ID")
    public ResponseEntity<PostsByAuthorIdResponse> getPostsByAuthorId(
            @Parameter(description = "ID пользователя")
            @PathVariable(value = "authorId") Long authorId,

            @Parameter(description = "Номер страницы")
            @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,

            @Parameter(description = "Размер страницы")
            @RequestParam(value = "size", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer size) {
        PostsByAuthorIdResponse response = postService.findPostsByAuthorId(authorId, page, size);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("{publisherId}/change-subscription")
    @Operation(summary = "Подписка/Отписка на пользователя по ID")
    public ResponseEntity<UserDTO> changeSubscription(
            @Parameter(description = "Получение текущего пользователя")
            @AuthenticationPrincipal UserPrincipal subscriber,

            @Parameter(description = "ID пользователя, на которого подписываются/отписываются")
            @PathVariable(name = "publisherId") Long publisherId) {
        UserDTO publisher = subscriptionService.changeSubscription(subscriber, publisherId);

        return ResponseEntity.ok(publisher);
    }
}
