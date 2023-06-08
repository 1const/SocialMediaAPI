package com.akmal.socialmediaapi.dto;


import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private String username;

    private String mail;

    private List<PostDTO> posts;
}
