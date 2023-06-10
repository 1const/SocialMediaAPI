package com.akmal.socialmediaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PostDTO {

    @Schema(description = "Заголовок Поста")
    @NotBlank
    private String header;

    @Schema(description = "Текст Поста")
    @NotBlank
    private String text;

    @Schema(description = "Путь на картинку")
    @NotBlank
    private String picturePath;

    @Schema(description = "ID автора Поста")
    private Long authorId;
}
