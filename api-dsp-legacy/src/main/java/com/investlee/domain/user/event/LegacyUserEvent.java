package com.investlee.domain.user.event;

import com.investlee.domain.AggregateType;
import com.investlee.domain.DomainEvent;
import com.investlee.domain.user.LegacyUserEntity;

import java.time.LocalDateTime;

public abstract class LegacyUserEvent implements DomainEvent {

    protected LegacyUserEntity legacyUserEntity;

    public LegacyUserEvent(LegacyUserEntity legacyUser) {
        this.legacyUserEntity = legacyUser;
    }

    @Override
    public AggregateType aggregateType() {
        return AggregateType.USER;
    }

    @Override
    public Long aggregateId() {
        return legacyUserEntity.getId();
    }

    @Override
    public Long ownerId() {
        return legacyUserEntity.getId();
    }

    @Override
    public abstract LocalDateTime occurredOn();
}
