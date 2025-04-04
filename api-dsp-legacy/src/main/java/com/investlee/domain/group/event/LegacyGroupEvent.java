package com.investlee.domain.group.event;

import com.investlee.domain.AggregateType;
import com.investlee.domain.DomainEvent;
import com.investlee.domain.group.LegacyGroupEntity;

import java.time.LocalDateTime;

public abstract class LegacyGroupEvent implements DomainEvent {

    protected LegacyGroupEntity legacyGroupEntity;

    public LegacyGroupEvent(LegacyGroupEntity legacyGroupEntity) {
        this.legacyGroupEntity = legacyGroupEntity;
    }

    @Override
    public AggregateType aggregateType() {
        return AggregateType.GROUP;
    }

    @Override
    public Long aggregateId() {
        return legacyGroupEntity.getId();
    }

    @Override
    public Long ownerId() {
        return legacyGroupEntity.getUserId();
    }

    @Override
    public abstract LocalDateTime occurredOn();

}