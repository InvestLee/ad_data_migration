package com.investlee.core.event;

import com.investlee.domain.AggregateType;
import com.investlee.domain.DomainEvent;

import java.time.LocalDateTime;

public record DomainMessage(
        AggregateType aggregateType,
        Long aggregateId,
        Long ownerId,
        LocalDateTime occurredOn) {
    public static DomainMessage from(DomainEvent event) {
        return new DomainMessage(
                event.aggregateType(),
                event.aggregateId(),
                event.ownerId(),
                event.occurredOn());
    }
}
