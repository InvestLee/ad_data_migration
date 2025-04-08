package com.investlee.message;

import com.investlee.domain.AggregateType;

public record PageMigrationMessage(
        Long userId,
        AggregateType aggregateType,
        boolean isFinished
) {
}
