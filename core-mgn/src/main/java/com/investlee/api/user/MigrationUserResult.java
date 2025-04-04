package com.investlee.api.user;

import com.investlee.domain.migration.user.MigrationUserEntity;
import com.investlee.domain.migration.user.MigrationUserStatus;

import java.time.LocalDateTime;

public record MigrationUserResult(
        Long id,
        MigrationUserStatus status,
        MigrationUserStatus prevStatus,
        LocalDateTime agreedDate,
        LocalDateTime updateDate) {

    public static MigrationUserResult from(MigrationUserEntity migrationUserEntity) {
        return new MigrationUserResult(
                migrationUserEntity.getId(),
                migrationUserEntity.getStatus(),
                migrationUserEntity.getPrevStatus(),
                migrationUserEntity.getAgreedAt(),
                migrationUserEntity.getUpdateAt()
        );
    }
}