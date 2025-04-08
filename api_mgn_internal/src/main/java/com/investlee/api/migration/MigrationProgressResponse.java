package com.investlee.api.migration;

import com.investlee.api.monitoring.MigrationMonitorMetrics;
import com.investlee.domain.migration.user.MigrationUserStatus;

import java.util.Map;

public record MigrationProgressResponse(
        Map<MigrationUserStatus, Long> statusStatistics,
        Long adGroupMigratedCount,
        Long adGroupTotalCount,
        Long keywordMigratedCount,
        Long keywordTotalCount
) {
    public static MigrationProgressResponse from(MigrationMonitorMetrics metrics) {
        return new MigrationProgressResponse(
                metrics.statusStatistics(),
                metrics.adGroupMigratedCount(),
                metrics.adGroupTotalCount(),
                metrics.keywordMigratedCount(),
                metrics.keywordTotalCount()
        );
    }
}