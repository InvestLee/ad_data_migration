package com.investlee.message;

import com.investlee.domain.migration.user.MigrationUserStatus;

public record MigrationUserMessage(
        Long userId,
        MigrationUserStatus status,
        MigrationUserStatus prevStatus
) {
}