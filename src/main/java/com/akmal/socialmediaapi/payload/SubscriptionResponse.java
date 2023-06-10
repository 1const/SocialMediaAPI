package com.akmal.socialmediaapi.payload;

import com.akmal.socialmediaapi.domain.Subscription;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class SubscriptionResponse {
    private Set<Subscription> subscriptions;
}
