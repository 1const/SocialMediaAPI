package com.akmal.socialmediaapi.service;

import com.akmal.socialmediaapi.domain.Subscription;
import com.akmal.socialmediaapi.domain.User;
import com.akmal.socialmediaapi.dto.SubscriptionDTO;
import com.akmal.socialmediaapi.dto.UserDTO;
import com.akmal.socialmediaapi.repository.SubscriptionRepository;
import com.akmal.socialmediaapi.repository.UserRepository;
import com.akmal.socialmediaapi.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Transactional
    public UserDTO changeSubscription(UserPrincipal subscriberPrincipal, Long publisherId) {
        User subscriber = userRepository.findById(subscriberPrincipal.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        User publisher = userRepository.findById(publisherId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Optional<Subscription> publisherSubscriber = publisher.getSubscribers()
                .stream()
                .filter(subscription -> subscription.getSubscriber().equals(subscriber))
                .findAny();

        if (publisherSubscriber.isEmpty()) {
            Subscription subscription = new Subscription(subscriber, publisher);

            publisher.getSubscribers().add(subscription);
            subscriber.getSubscriptions().add(subscription);
        } else {
            publisher.getSubscribers().remove(publisherSubscriber.get());
            subscriber.getSubscriptions().remove(publisherSubscriber.get());
        }

        userRepository.save(publisher);

        return modelMapper.map(publisher, UserDTO.class);
    }

    @Transactional
    public SubscriptionDTO changeSubscriptionStatus(UserPrincipal publisherPrincipal, Long subscriberId) {
        User publisher = userRepository.findById(publisherPrincipal.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        User subscriber = userRepository.findById(subscriberId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Subscription subscription = subscriptionRepository
                .findByPublisherAndSubscriber(publisher, subscriber)
                .orElseThrow(() -> new EntityNotFoundException("Subscription not found"));

        subscription.setFriend(!subscription.isFriend());
        if (subscription.isFriend()) {
            publisher.getSubscriptions().remove(new Subscription(subscriber, publisher));
        } else {
            publisher.getSubscriptions().add(new Subscription(subscriber, publisher));
        }

        return modelMapper.map(subscriptionRepository.save(subscription), SubscriptionDTO.class);
    }
}
