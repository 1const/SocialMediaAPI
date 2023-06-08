package com.akmal.socialmediaapi.service;

import com.akmal.socialmediaapi.domain.User;
import com.akmal.socialmediaapi.dto.UserDTO;
import com.akmal.socialmediaapi.repository.UserRepository;
import com.akmal.socialmediaapi.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserDTO getCurrentUser(UserPrincipal userPrincipal) {
        User user = userRepository
                .findById(userPrincipal.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return modelMapper.map(user, UserDTO.class);
    }

}
