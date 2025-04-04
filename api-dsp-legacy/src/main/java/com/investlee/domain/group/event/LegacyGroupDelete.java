package com.investlee.domain.group.event;

import com.investlee.domain.group.LegacyGroupEntity;

import java.time.LocalDateTime;

public class LegacyGroupDelete extends LegacyGroupEvent {
    public LegacyGroupDelete(LegacyGroupEntity legacyGroupEntity) {
        super(legacyGroupEntity);
    }

    @Override
    public LocalDateTime occurredOn() {
        return legacyGroupEntity.getDeletedAt();
    }
}
