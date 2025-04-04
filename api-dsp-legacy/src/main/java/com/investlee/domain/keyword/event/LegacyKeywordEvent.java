package com.investlee.domain.keyword.event;

import com.investlee.domain.AggregateType;
import com.investlee.domain.DomainEvent;
import com.investlee.domain.keyword.LegacyKeywordEntity;

import java.time.LocalDateTime;

public abstract class LegacyKeywordEvent implements DomainEvent {
    protected LegacyKeywordEntity legacyKeywordEntity;

    public LegacyKeywordEvent(LegacyKeywordEntity legacyKeywordEntity) {
        this.legacyKeywordEntity = legacyKeywordEntity;
    }

    @Override
    public AggregateType aggregateType() {
        return AggregateType.KEYWORD;
    }

    @Override
    public Long aggregateId() {
        return legacyKeywordEntity.getId();
    }

    @Override
    public Long ownerId() {
        return legacyKeywordEntity.getUserId();
    }

    @Override
    public abstract LocalDateTime occurredOn();
}
