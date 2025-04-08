package com.investlee.message;

import com.investlee.api.dispatcher.ParentPageMigrationDispatcher;
import com.investlee.api.user.MigrationUserService;
import com.investlee.api.user.StartMigrationFailedException;
import com.investlee.domain.AggregateType;
import com.investlee.domain.migration.user.MigrationUserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MigrationMessageProcessor {

    private final MigrationUserService migrationUserService;
    private final ParentPageMigrationDispatcher parentPageMigrationDispatcher;

    @Async
    public void progressMigration(
            MigrationUserStatus prevStatus,
            MigrationUserStatus status,
            Long userId
    ) {
        switch (status) {
            case RETRIED -> progressMigration(prevStatus, userId);
            case AGREED,
                 USER_FINISHED,
                 ADGROUP_FINISHED,
                 KEYWORD_FINISHED -> progressMigration(status, userId);
            default -> log.info("userId: {}, status: {}, prevStatus: {}", userId, status, prevStatus);
        }
    }

    public void progressMigration(
            MigrationUserStatus status,
            Long userId
    ) {
        switch (status) {
            case AGREED -> startMigration(userId);
            case USER_FINISHED -> dispatch(userId, AggregateType.GROUP);
            case ADGROUP_FINISHED -> dispatch(userId, AggregateType.KEYWORD);
            case KEYWORD_FINISHED -> migrationUserService.progressMigration(userId);
        }
    }

    private void startMigration(Long userId) {
        try {
            migrationUserService.startMigration(userId);
        } catch (StartMigrationFailedException e) {
            log.error("start migration failed", e);
        }
    }

    @Async
    public void processPageMigration(
            Long userId,
            AggregateType aggregateType,
            boolean isFinished
    ) {
        if (isFinished) {
            migrationUserService.progressMigration(userId);
        } else {
            dispatch(
                    userId,
                    aggregateType
            );
        }
    }

    private void dispatch(
            Long userId,
            AggregateType aggregateType
    ) {
        parentPageMigrationDispatcher.dispatch(
                userId,
                aggregateType
        );
    }
}
