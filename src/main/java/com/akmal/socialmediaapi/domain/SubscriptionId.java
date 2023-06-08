package com.akmal.socialmediaapi.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionId implements Serializable {

    @Column(name = "publisher_id")
    private Long publisherId;

    @Column(name = "subscriber_id")
    private Long subscriberId;
}
