package com.investlee.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class MigrationMessageConsumer {

    private final MigrationMessageProcessor processor;

    @Bean
    public Consumer<MigrationUserMessage> migrationUserConsumer() {
        return message -> {
            processor.progressMigration(
                    message.prevStatus(),
                    message.status(),
                    message.userId()
            );
            log.info("migration user consumer: {}", message);
        };
    }

    @Bean
    public Consumer<PageMigrationMessage> pageMigrationConsumer() {
        return message -> {
            processor.processPageMigration(
                    message.userId(),
                    message.aggregateType(),
                    message.isFinished()
            );
            log.info("page migration consumer: {}", message);
        };
    }
}
