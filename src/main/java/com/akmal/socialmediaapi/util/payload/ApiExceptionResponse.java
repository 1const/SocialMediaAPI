package com.akmal.socialmediaapi.util.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiExceptionResponse {

    private String errorMessage;

}
