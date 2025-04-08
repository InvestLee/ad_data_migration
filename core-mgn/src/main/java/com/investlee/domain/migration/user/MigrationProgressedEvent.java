package com.investlee.domain.migration.user;

public class MigrationProgressedEvent extends MigrationUserEvent {
    public MigrationProgressedEvent(MigrationUserEntity migrationUserEntity) {
        super(migrationUserEntity);
    }
}