package com.akmal.socialmediaapi.util.payload;

import com.akmal.socialmediaapi.dto.PostDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Ответ, содержащий ленту активности")
public class ActivityFeedResponse {

    @Schema(description = "Лента Активности")
    private List<PostDTO> activityFeed;

    @Schema(description = "Текущая страница")
    private Integer pageNumber;

    @Schema(description = "Количество страниц")
    private Integer totalPages;
}
