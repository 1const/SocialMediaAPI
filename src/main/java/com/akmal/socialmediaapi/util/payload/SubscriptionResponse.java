package com.akmal.socialmediaapi.util.payload;

import com.akmal.socialmediaapi.domain.Subscription;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class SubscriptionResponse {
    private Set<Subscription> subscriptions = new HashSet<>();
}
