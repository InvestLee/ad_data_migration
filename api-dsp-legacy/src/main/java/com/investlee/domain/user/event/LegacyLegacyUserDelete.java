package com.investlee.domain.user.event;

import com.investlee.domain.user.LegacyUserEntity;

import java.time.LocalDateTime;

public class LegacyLegacyUserDelete extends LegacyUserEvent {
    public LegacyLegacyUserDelete(LegacyUserEntity legacyUserEntity) {
        super(legacyUserEntity);
    }

    @Override
    public LocalDateTime occurredOn() {
        return legacyUserEntity.getDeletedAt();
    }
}
