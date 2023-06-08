package com.akmal.socialmediaapi.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
public class Subscription {

    @EmbeddedId
    private SubscriptionId id;

    @Column(name = "is_friend")
    private boolean isFriend;

    @MapsId(value = "publisherId")
    @ManyToOne
    private User publisher;

    @MapsId(value = "subscriberId")
    @ManyToOne
    private User subscriber;

    public Subscription(User publisher, User subscriber) {
        this.publisher = publisher;
        this.subscriber = subscriber;
        this.id = new SubscriptionId(publisher.getId(), subscriber.getId());
    }
}
