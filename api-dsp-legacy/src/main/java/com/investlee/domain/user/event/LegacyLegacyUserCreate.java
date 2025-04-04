package com.investlee.domain.user.event;

import com.investlee.domain.user.LegacyUserEntity;

import java.time.LocalDateTime;

public class LegacyLegacyUserCreate extends LegacyUserEvent {
    public LegacyLegacyUserCreate(LegacyUserEntity legacyUserEntity) {
        super(legacyUserEntity);
    }

    @Override
    public LocalDateTime occurredOn() {
        return legacyUserEntity.getCreatedAt();
    }
}
