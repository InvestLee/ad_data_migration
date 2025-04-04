package com.investlee.domain.migration.user;

public class MigrationProgressedEventMigration extends MigrationUserEvent {
    public MigrationProgressedEventMigration(MigrationUserEntity migrationUserEntity) {
        super(migrationUserEntity);
    }
}