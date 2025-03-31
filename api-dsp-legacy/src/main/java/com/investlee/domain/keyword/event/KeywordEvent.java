package com.investlee.domain.keyword.event;

import com.investlee.domain.AggregateType;
import com.investlee.domain.DomainEvent;
import com.investlee.domain.keyword.KeywordEntity;

import java.time.LocalDateTime;

public abstract class KeywordEvent implements DomainEvent {
    protected KeywordEntity keywordEntity;

    public KeywordEvent(KeywordEntity keywordEntity) {
        this.keywordEntity = keywordEntity;
    }

    @Override
    public AggregateType aggregateType() {
        return AggregateType.KEYWORD;
    }

    @Override
    public Long aggregateId() {
        return keywordEntity.getId();
    }

    @Override
    public Long ownerId() {
        return keywordEntity.getUserId();
    }

    @Override
    public abstract LocalDateTime occurredOn();
}
