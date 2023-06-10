package com.akmal.socialmediaapi.payload;

import com.akmal.socialmediaapi.dto.PostDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PostsByAuthorIdResponse {

    @Schema(description = "Посты пользователя по ID")
    private List<PostDTO> posts;
}
