package com.investlee.domain.group.event;

import com.investlee.domain.group.LegacyGroupEntity;

import java.time.LocalDateTime;

public class LegacyGroupLinkUrlUpdate extends LegacyGroupEvent {
    public LegacyGroupLinkUrlUpdate(LegacyGroupEntity legacyGroupEntity) {
        super(legacyGroupEntity);
    }

    @Override
    public LocalDateTime occurredOn() {
        return legacyGroupEntity.getUpdatedAt();
    }
}
