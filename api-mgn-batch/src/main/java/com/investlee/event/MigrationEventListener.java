package com.investlee.event;

import com.investlee.domain.migration.PageMigrationEvent;
import com.investlee.domain.migration.user.MigrationProgressedEvent;
import com.investlee.message.MigrationUserMessage;
import com.investlee.message.PageMigrationMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class MigrationEventListener {

    private static final String MIGRATION_USER_OUT = "migration-user-out";
    private static final String PAGE_MIGRATION_OUT = "page-migration-out";

    private static final String PARTITION_KEY = "partitionKey";

    private final StreamBridge streamBridge;


    @TransactionalEventListener
    public void handleMigrationProgressedEvent(MigrationProgressedEvent event) {
        log.info("migration progressed event listener: {}", event.toString());
        streamBridge.send(MIGRATION_USER_OUT,
                MessageBuilder.createMessage(
                        new MigrationUserMessage(event.getUserId(), event.getStatus(), event.getPrevStatus()),
                        header(event.getUserId())));
    }

    private MessageHeaders header(Long id) {
        return new MessageHeaders(Map.of(PARTITION_KEY, id));
    }

    @TransactionalEventListener
    public void handlePageMigrationEvent(PageMigrationEvent event) {
        log.info("page migration event listener: {}", event.toString());
        streamBridge.send(PAGE_MIGRATION_OUT, MessageBuilder.createMessage(
                new PageMigrationMessage(event.userId(), event.aggregateType(), event.isFinished()),
                header(event.userId())));
    }
}
