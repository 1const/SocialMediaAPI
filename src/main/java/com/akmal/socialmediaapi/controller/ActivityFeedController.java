package com.akmal.socialmediaapi.controller;

import com.akmal.socialmediaapi.security.UserPrincipal;
import com.akmal.socialmediaapi.service.PostService;
import com.akmal.socialmediaapi.util.payload.ActivityFeedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/social-media-api/v1/activity-feed")
@RequiredArgsConstructor
@Tag(name = "Контроллер для работы с лентой активности")
public class ActivityFeedController {

    private final String DEFAULT_PAGE_NUMBER = "0";
    private final String DEFAULT_PAGE_SIZE = "5";

    private final PostService postService;

    @GetMapping
    @Operation(description = "Получить ленту активности, отсортированную по времени")
    public ResponseEntity<ActivityFeedResponse> getActivityFeed(
            @Parameter(description = "Текущий пользователь")
            @AuthenticationPrincipal UserPrincipal userPrincipal,

            @Parameter(description = "Номер страницы")
            @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,

            @Parameter(description = "Размер страницы")
            @RequestParam(value = "size", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer size) {
        ActivityFeedResponse response = postService.getActivityFeed(userPrincipal, page, size);

        return ResponseEntity.ok(response);
    }

}
