package com.investlee.domain.migration.user;

public class MigrationUserEvent {

    protected MigrationUserEntity migrationUserEntity;

    public MigrationUserEvent(MigrationUserEntity migrationUserEntity) {
        this.migrationUserEntity = migrationUserEntity;
    }

    public Long getUserId() {
        return migrationUserEntity.getId();
    }

    public MigrationUserStatus getStatus() {
        return migrationUserEntity.getStatus();
    }

    public MigrationUserStatus getPrevStatus() {
        return migrationUserEntity.getPrevStatus();
    }
}