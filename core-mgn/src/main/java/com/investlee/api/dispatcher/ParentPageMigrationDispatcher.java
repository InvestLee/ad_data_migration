package com.investlee.api.dispatcher;

import com.investlee.api.legacy.PageLegacyMigrationLog;
import com.investlee.api.legacy.PageMigrationResult;
import com.investlee.api.legacy.ParentPageLegacyMigrationService;
import com.investlee.api.legacy.group.ParentPageLegacyGroupMigrationService;
import com.investlee.api.legacy.keyword.ParentPageLegacyKeywordMigrationService;
import com.investlee.domain.AggregateType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ParentPageMigrationDispatcher {

    private final ParentPageLegacyGroupMigrationService groupMigrationService;
    private final ParentPageLegacyKeywordMigrationService keywordMigrationService;

    public boolean dispatch(
            Long userId,
            AggregateType aggregateType
    ) {
        ParentPageLegacyMigrationService<?, ?, ?, ?> service = switch (aggregateType) {
            case GROUP -> groupMigrationService;
            case KEYWORD -> keywordMigrationService;
            default -> throw new PageLegacyMigrationServiceNotFoundException();
        };
        PageMigrationResult result = service.migrate(userId);
        logMigrationResult(
                result,
                aggregateType
        );
        return result.isSuccess();
    }

    private void logMigrationResult(
            PageMigrationResult result,
            AggregateType aggregateType
    ) {
        if (result.isSuccess()) {
            log.info("{}", PageLegacyMigrationLog.success(result, aggregateType));
        } else {
            log.error("{}", PageLegacyMigrationLog.fail(result, aggregateType));
        }
    }
}