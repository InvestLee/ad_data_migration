package com.investlee.domain.keyword.event;

import com.investlee.domain.keyword.LegacyKeywordEntity;

import java.time.LocalDateTime;

public class LegacyKeywordDelete extends LegacyKeywordEvent {
    public LegacyKeywordDelete(LegacyKeywordEntity legacyKeywordEntity) {
        super(legacyKeywordEntity);
    }

    @Override
    public LocalDateTime occurredOn() {
        return legacyKeywordEntity.getDeletedAt();
    }
}
