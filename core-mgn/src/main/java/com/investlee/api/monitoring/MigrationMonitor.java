package com.investlee.api.monitoring;

import com.investlee.domain.migration.PageMigrations;
import com.investlee.domain.migration.group.GroupPageMigrationRepository;
import com.investlee.domain.migration.keyword.KeywordPageMigrationRepository;
import com.investlee.domain.migration.user.MigrationUserRepository;
import com.investlee.domain.migration.user.MigrationUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MigrationMonitor {

    private final MigrationUserRepository migrationUserRepository;
    private final GroupPageMigrationRepository adGroupPageMigrationRepository;
    private final KeywordPageMigrationRepository keywordPageMigrationRepository;

    public MigrationMonitorMetrics measure() {
        MigrationUsers migrationUsers
                = MigrationUsers.of(migrationUserRepository.findAll());
        PageMigrations adGroupMigrations
                = PageMigrations.of(adGroupPageMigrationRepository.findAll());
        PageMigrations keywordMigrations
                = PageMigrations.of(keywordPageMigrationRepository.findAll());

        return MigrationMonitorMetrics.from(
                migrationUsers,
                adGroupMigrations,
                keywordMigrations
        );
    }

}