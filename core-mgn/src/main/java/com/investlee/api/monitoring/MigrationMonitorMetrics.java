package com.investlee.api.monitoring;

import com.investlee.domain.migration.PageMigrations;
import com.investlee.domain.migration.user.MigrationUserStatus;
import com.investlee.domain.migration.user.MigrationUsers;

import java.util.Map;

public record MigrationMonitorMetrics(
        Map<MigrationUserStatus, Long> statusStatistics,
        Long adGroupMigratedCount, Long adGroupTotalCount,
        Long keywordMigratedCount, Long keywordTotalCount
) {

    public static MigrationMonitorMetrics from(
            MigrationUsers migrationUsers,
            PageMigrations adGroupMigrations,
            PageMigrations keywordMigrations
    ) {
        return new MigrationMonitorMetrics(
                migrationUsers.statusStatistics(),
                adGroupMigrations.migratedCount(),
                adGroupMigrations.totalCount(),
                keywordMigrations.migratedCount(),
                keywordMigrations.totalCount()
        );
    }
}