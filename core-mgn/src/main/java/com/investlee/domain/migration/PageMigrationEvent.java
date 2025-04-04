package com.investlee.domain.migration;

import com.investlee.domain.AggregateType;

public record PageMigrationEvent(
        AggregateType aggregateType,
        Long userId,
        boolean isFinished
) {

}