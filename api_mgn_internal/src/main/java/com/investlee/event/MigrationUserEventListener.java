package com.investlee.event;

import com.investlee.domain.migration.user.MigrationAgreedEvent;
import com.investlee.domain.migration.user.MigrationRetriedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class MigrationUserEventListener {

    private static final String OUTPUT_BINDING = "migration-user-out";
    private final StreamBridge streamBridge;

    @TransactionalEventListener
    public void handleAgreedEvent(MigrationAgreedEvent event) {
        sendMigrationUserMessage(
                MigrationUserMessage.from(event),
                event.getUserId()
        );
    }

    private void sendMigrationUserMessage(MigrationUserMessage message, Long userId) {
        streamBridge.send(OUTPUT_BINDING,
                MessageBuilder.createMessage(message,
                        new MessageHeaders(Map.of("partitionKey", userId))));
    }

    @TransactionalEventListener
    public void handleRetriedEvent(MigrationRetriedEvent event) {
        sendMigrationUserMessage(
                MigrationUserMessage.from(event),
                event.getUserId()
        );
    }
}