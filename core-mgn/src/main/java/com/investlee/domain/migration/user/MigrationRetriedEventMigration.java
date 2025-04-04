package com.investlee.domain.migration.user;

public class MigrationRetriedEventMigration extends MigrationUserEvent {
    public MigrationRetriedEventMigration(MigrationUserEntity migrationUserEntity) {
        super(migrationUserEntity);
    }
}