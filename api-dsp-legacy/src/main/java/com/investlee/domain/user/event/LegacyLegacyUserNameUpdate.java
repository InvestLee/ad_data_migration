package com.investlee.domain.user.event;

import com.investlee.domain.user.LegacyUserEntity;

import java.time.LocalDateTime;

public class LegacyLegacyUserNameUpdate extends LegacyUserEvent {
    public LegacyLegacyUserNameUpdate(LegacyUserEntity legacyUserEntity) {
        super(legacyUserEntity);
    }

    @Override
    public LocalDateTime occurredOn() {
        return legacyUserEntity.getUpdatedAt();
    }
}
