package com.investlee.domain.keyword.event;

import com.investlee.domain.keyword.KeywordEntity;

import java.time.LocalDateTime;

public class KeywordDelete extends KeywordEvent {
    public KeywordDelete(KeywordEntity keywordEntity) {
        super(keywordEntity);
    }

    @Override
    public LocalDateTime occurredOn() {
        return keywordEntity.getDeletedAt();
    }
}
