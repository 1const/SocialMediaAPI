package com.akmal.socialmediaapi.controller;

import com.akmal.socialmediaapi.dto.AuthenticationDTO;
import com.akmal.socialmediaapi.util.JwtUtil;
import com.akmal.socialmediaapi.service.RegistrationService;
import com.akmal.socialmediaapi.payload.JwtResponse;
import com.akmal.socialmediaapi.payload.RegistrationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/social-media-api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Вход API", description = "Позволяет зарегистрироваться или войти в API")
public class AuthController {

    private final RegistrationService registrationService;

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/registration")
    @Operation(summary = "Точка регистрации пользователя")
    public ResponseEntity<JwtResponse> performRegistration(
            @RequestBody
            @Valid
            @Parameter(description = "Запрос для регистрации, содержащий имя, почта и пароль")
            RegistrationRequest registrationRequest) {

        registrationService.register(registrationRequest);

        String jwt = jwtUtil.generateTokenByUsername(registrationRequest.getUsername());

        return new ResponseEntity<>(new JwtResponse(jwt),
                HttpStatus.CREATED);
    }


    @PostMapping("/login")
    @Operation(summary = "Точка входа пользователя")
    public ResponseEntity<JwtResponse> performLogin(
            @RequestBody
            @Valid
            @Parameter(description = "Запрос для входа, содержащий имя и пароль")
            AuthenticationDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(),
                        authenticationDTO.getPassword());

        authenticationManager.authenticate(token); //TODO handle BadCredentialsException

        String jwt = jwtUtil.generateTokenByUsername(authenticationDTO.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
}