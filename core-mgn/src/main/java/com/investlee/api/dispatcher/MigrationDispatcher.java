package com.investlee.api.dispatcher;

import com.investlee.api.legacy.MigrationService;
import com.investlee.api.legacy.campaign.LegacyCampaignMigrationService;
import com.investlee.api.legacy.group.LegacyGroupMigrationService;
import com.investlee.api.legacy.keyword.LegacyKeywordMigrationService;
import com.investlee.api.legacy.user.LegacyUserMigrationService;
import com.investlee.api.user.MigrationUserService;
import com.investlee.domain.AggregateType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MigrationDispatcher {

    private final MigrationUserService migrationUserService;
    private final LegacyUserMigrationService legacyUserMigrationService;
    private final LegacyCampaignMigrationService legacyCampaignMigrationService;
    private final LegacyGroupMigrationService legacyGroupMigrationService;
    private final LegacyKeywordMigrationService legacyKeywordMigrationService;


    public boolean dispatch(Long userId, Long aggregateId, AggregateType aggregateType) {
        if (isDisagreed(userId)) {
            return false;
        }
        return migrate(aggregateId, aggregateType);
    }

    private boolean isDisagreed(Long userId) {
        return migrationUserService.isDisagreed(userId);
    }


    private boolean migrate(
            Long aggregateId,
            AggregateType aggregateType) {
        MigrationService service = switch (aggregateType) {
            case USER -> legacyUserMigrationService;
            case CAMPAIGN -> legacyCampaignMigrationService;
            case GROUP -> legacyGroupMigrationService;
            case KEYWORD -> legacyKeywordMigrationService;
        };
        boolean result = service.migrate(aggregateId);
        logMigrationResult(
                result,
                aggregateType,
                aggregateId
        );
        return result;
    }

    private void logMigrationResult(
            boolean result,
            AggregateType aggregateType,
            Long aggregateId
    ) {
        if (result) {
            log.info("{}", LegacyMigrationLog.success(aggregateType, aggregateId));
        } else {
            log.error("{}", LegacyMigrationLog.fail(aggregateType, aggregateId));
        }
    }
}
