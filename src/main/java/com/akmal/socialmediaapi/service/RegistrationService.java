package com.akmal.socialmediaapi.service;

import com.akmal.socialmediaapi.domain.User;
import com.akmal.socialmediaapi.dto.UserDTO;
import com.akmal.socialmediaapi.repository.UserRepository;
import com.akmal.socialmediaapi.util.payload.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;


    @Transactional
    public void register(RegistrationRequest registrationRequest) {
        User user = modelMapper.map(registrationRequest, User.class);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");

        userRepository.save(user);
    }
}
