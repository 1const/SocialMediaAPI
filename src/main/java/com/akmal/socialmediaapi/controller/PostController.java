package com.akmal.socialmediaapi.controller;

import com.akmal.socialmediaapi.dto.PostDTO;
import com.akmal.socialmediaapi.security.UserPrincipal;
import com.akmal.socialmediaapi.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("social-media-api/v1/posts")
@RequiredArgsConstructor
@Tag(name = "Контроллер для работа с постами")
public class PostController {

    private final PostService postService;


    @GetMapping("/{id}")
    @Operation(description = "Поиск поста по ID")
    public ResponseEntity<PostDTO> findPostById(
            @Parameter(description = "ID поста")
            @PathVariable(name = "id") Long id) {
        PostDTO postDTO = postService.findPostById(id);

        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @PostMapping
    @Operation(description = "Сохранение поста")
    public ResponseEntity<PostDTO> savePost(
            @Parameter(description = "Объект нового поста")
            @RequestBody PostDTO postDTO,

            @Parameter(description = "Текущий пользователь")
            @AuthenticationPrincipal UserPrincipal currentUser) {
        PostDTO returnedPostDTO = postService.savePost(postDTO, currentUser);

        return new ResponseEntity<>(returnedPostDTO, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @Operation(description = "Обновление поста")
    public ResponseEntity<PostDTO> updatePost(
            @Parameter(description = "id поста для обновления")
            @PathVariable(name = "id") Long postId,

            @Parameter(description = "Объект обновленного поста")
            @RequestBody PostDTO postDTO,

            @Parameter(description = "Текущий пользователь")
            @AuthenticationPrincipal UserPrincipal currentUser) {
        PostDTO returnedPostDTO = postService.updatePost(postId, postDTO, currentUser);

        return new ResponseEntity<>(returnedPostDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление поста")
    public ResponseEntity<HttpStatus> deletePost(
            @Parameter(description = "ID поста для удаления")
            @PathVariable(name = "id") Long postId,

            @Parameter(description = "Текущий пользователь")
            @AuthenticationPrincipal UserPrincipal currentUser) {
        postService.deletePost(postId, currentUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
