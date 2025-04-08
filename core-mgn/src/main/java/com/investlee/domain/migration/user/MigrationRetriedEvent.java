package com.investlee.domain.migration.user;

public class MigrationRetriedEvent extends MigrationUserEvent {
    public MigrationRetriedEvent(MigrationUserEntity migrationUserEntity) {
        super(migrationUserEntity);
    }
}