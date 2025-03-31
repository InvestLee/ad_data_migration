package com.investlee.core.event;

import com.investlee.domain.DomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class DomainEventListener {
    private static final String OUTPUT_BINDING = "legacy-rabbit-out";
    private final StreamBridge streamBridge;

    @TransactionalEventListener
    public void handleEvent(DomainEvent event) {
        streamBridge.send(OUTPUT_BINDING, DomainMessage.from(event));
    }
}
