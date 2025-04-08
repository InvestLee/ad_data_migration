package com.investlee.api.user;

import com.investlee.domain.migration.user.MigrationUserStatus;

import java.time.LocalDateTime;

public record MigrationUserResponse(
        Long id,
        MigrationUserStatus status,
        LocalDateTime agreedDate,
        LocalDateTime updateDate
) {
}