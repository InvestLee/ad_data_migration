package com.investlee.domain.user.event;

import com.investlee.domain.AggregateType;
import com.investlee.domain.DomainEvent;
import com.investlee.domain.user.UserEntity;

import java.time.LocalDateTime;

public abstract class UserEvent implements DomainEvent {

    protected UserEntity userEntity;

    public UserEvent(UserEntity legacyUser) {
        this.userEntity = legacyUser;
    }

    @Override
    public AggregateType aggregateType() {
        return AggregateType.USER;
    }

    @Override
    public Long aggregateId() {
        return userEntity.getId();
    }

    @Override
    public Long ownerId() {
        return userEntity.getId();
    }

    @Override
    public abstract LocalDateTime occurredOn();
}
