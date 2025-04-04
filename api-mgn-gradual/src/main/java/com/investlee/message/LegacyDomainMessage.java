package com.investlee.message;

import com.investlee.domain.AggregateType;

import java.time.LocalDateTime;

public record LegacyDomainMessage(
        AggregateType aggregateType,
        Long aggregateId, Long ownerId,
        LocalDateTime occurredOn) {

}