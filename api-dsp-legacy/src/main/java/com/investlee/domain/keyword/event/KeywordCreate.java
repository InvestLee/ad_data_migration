package com.investlee.domain.keyword.event;

import com.investlee.domain.keyword.KeywordEntity;

import java.time.LocalDateTime;

public class KeywordCreate extends KeywordEvent {
    public KeywordCreate(KeywordEntity keywordEntity) {
        super(keywordEntity);
    }

    @Override
    public LocalDateTime occurredOn() {
        return keywordEntity.getCreatedAt();
    }
}
