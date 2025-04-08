package com.investlee.domain.migration.user;

public class MigrationAgreedEvent extends MigrationUserEvent {
    public MigrationAgreedEvent(MigrationUserEntity migrationUserEntity) {
        super(migrationUserEntity);
    }
}