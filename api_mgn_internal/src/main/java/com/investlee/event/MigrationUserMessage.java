package com.investlee.event;

import com.investlee.domain.migration.user.MigrationUserEvent;
import com.investlee.domain.migration.user.MigrationUserStatus;

public record MigrationUserMessage(
        Long userId,
        MigrationUserStatus status,
        MigrationUserStatus prevStatus
) {
    public static MigrationUserMessage from(MigrationUserEvent event) {
        return new MigrationUserMessage(
                event.getUserId(),
                event.getStatus(),
                event.getPrevStatus()
        );
    }
}
