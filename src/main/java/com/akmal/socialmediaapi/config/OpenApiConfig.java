package com.akmal.socialmediaapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.stereotype.Component;

@OpenAPIDefinition(
        info = @Info(
                title = "Social Media APi",
                description = "Social Media", version = "1.0.0")
)
@Component
public class OpenApiConfig {
}
