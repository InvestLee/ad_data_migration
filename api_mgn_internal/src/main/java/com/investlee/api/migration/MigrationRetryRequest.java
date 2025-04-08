package com.investlee.api.migration;

import com.investlee.domain.AggregateType;

public record MigrationRetryRequest(
        Long userId,
        Long aggregateId,
        AggregateType aggregateType
) {
}
