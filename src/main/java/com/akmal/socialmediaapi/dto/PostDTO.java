package com.akmal.socialmediaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.File;

@Data
public class PostDTO {

    @Schema(description = "Заголовок Поста")
    private String header;

    @Schema(description = "Текст Поста")
    private String text;

    @Schema(description = "Путь на картинку")
    private String picturePath;

    @Schema(description = "ID автора Поста")
    private Long authorId;
}
