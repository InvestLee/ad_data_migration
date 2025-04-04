package com.investlee.domain.migration.user;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public record MigrationUsers(Iterable<MigrationUserEntity> migrationUsers) {

    public static MigrationUsers of(Iterable<MigrationUserEntity> migrationUsers) {
        return new MigrationUsers(migrationUsers);
    }

    public Map<MigrationUserStatus, Long> statusStatistics() {
        return StreamSupport.stream(migrationUsers.spliterator(), true)
                .collect(Collectors.groupingBy(MigrationUserEntity::getStatus, Collectors.counting()));
    }
}