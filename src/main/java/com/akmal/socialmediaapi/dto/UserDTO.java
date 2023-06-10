package com.akmal.socialmediaapi.dto;


import lombok.Data;


import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class UserDTO {

    @NotBlank
    private String username;

    private String mail;

    private List<PostDTO> posts;
}
