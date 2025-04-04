package com.investlee.domain.keyword.event;

import com.investlee.domain.keyword.LegacyKeywordEntity;

import java.time.LocalDateTime;

public class LegacyKeywordCreate extends LegacyKeywordEvent {
    public LegacyKeywordCreate(LegacyKeywordEntity legacyKeywordEntity) {
        super(legacyKeywordEntity);
    }

    @Override
    public LocalDateTime occurredOn() {
        return legacyKeywordEntity.getCreatedAt();
    }
}
