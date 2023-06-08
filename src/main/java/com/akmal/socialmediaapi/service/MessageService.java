package com.akmal.socialmediaapi.service;

import com.akmal.socialmediaapi.domain.Message;
import com.akmal.socialmediaapi.domain.User;
import com.akmal.socialmediaapi.dto.MessageDTO;
import com.akmal.socialmediaapi.exception.NotFriendsException;
import com.akmal.socialmediaapi.repository.MessageRepository;
import com.akmal.socialmediaapi.repository.UserRepository;
import com.akmal.socialmediaapi.security.UserPrincipal;
import com.akmal.socialmediaapi.util.payload.MessageRequest;
import com.akmal.socialmediaapi.util.payload.MessagesResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public MessagesResponse findMessagesByUserId(Long friendId,
                                                 UserPrincipal userPrincipal) {

        List<Message> messagesByFriendId = messageRepository
                .findMessagesByFriendId(friendId, userPrincipal.getUser().getId());

        List<MessageDTO> messages = messagesByFriendId.stream()
                .map(message -> modelMapper.map(message, MessageDTO.class))
                .toList();

        return new MessagesResponse(messages);
    }

    @Transactional
    public MessageDTO sendMessageToFriend(Long friendId,
                                          UserPrincipal userPrincipal,
                                          MessageRequest messageRequest) {
        User friend = userRepository.findById(friendId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        User user = userRepository.findById(userPrincipal.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (friend.getSubscribers()
                .stream()
                .filter(subscription ->
                        subscription.isFriend() && subscription.getSubscriber().equals(user))
                .findAny().isEmpty()) {
            throw new NotFriendsException("Сan't send messages to non-friends");
        }

        MessageDTO messageDTO = new MessageDTO(
                messageRequest.getText(),
                new Date(),
                user.getUsername(),
                friend.getUsername());

        Message message = modelMapper.map(messageDTO, Message.class);

        message.setSender(user);
        message.setReceiver(friend);

        messageRepository.save(message);

        return messageDTO;
    }
}
