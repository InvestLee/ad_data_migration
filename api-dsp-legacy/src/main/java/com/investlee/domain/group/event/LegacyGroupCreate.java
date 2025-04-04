package com.investlee.domain.group.event;

import com.investlee.domain.group.LegacyGroupEntity;

import java.time.LocalDateTime;

public class LegacyGroupCreate extends LegacyGroupEvent {
    public LegacyGroupCreate(LegacyGroupEntity legacyGroupEntity) {
        super(legacyGroupEntity);
    }

    @Override
    public LocalDateTime occurredOn() {
        return legacyGroupEntity.getCreatedAt();
    }
}
