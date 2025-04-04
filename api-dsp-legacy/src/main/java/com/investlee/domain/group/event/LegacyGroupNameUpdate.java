package com.investlee.domain.group.event;

import com.investlee.domain.group.LegacyGroupEntity;

import java.time.LocalDateTime;

public class LegacyGroupNameUpdate extends LegacyGroupEvent {
    public LegacyGroupNameUpdate(LegacyGroupEntity legacyGroupEntity) {
        super(legacyGroupEntity);
    }

    @Override
    public LocalDateTime occurredOn() {
        return legacyGroupEntity.getUpdatedAt();
    }
}
