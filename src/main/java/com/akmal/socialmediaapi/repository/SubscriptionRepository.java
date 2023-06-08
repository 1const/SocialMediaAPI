package com.akmal.socialmediaapi.repository;

import com.akmal.socialmediaapi.domain.Subscription;
import com.akmal.socialmediaapi.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findByPublisherAndSubscriber(User publisher, User subscriber);

    List<Subscription> findBySubscriber(User subscriber);
}
