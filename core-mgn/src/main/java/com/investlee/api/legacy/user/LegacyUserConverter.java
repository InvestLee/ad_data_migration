package com.investlee.api.legacy.user;

import com.investlee.api.legacy.LegacyConverter;
import com.investlee.domain.advance.user.AdvanceUserEntity;
import com.investlee.domain.legacy.user.LegacyUserEntity;
import org.springframework.stereotype.Component;

@Component
public class LegacyUserConverter implements LegacyConverter<
        LegacyUserEntity,
        AdvanceUserEntity> {

    public AdvanceUserEntity convert(LegacyUserEntity legacyUserEntity) {
        return AdvanceUserEntity.migrated(
                legacyUserEntity.getId(),
                legacyUserEntity.getName(),
                legacyUserEntity.getCreatedAt(),
                legacyUserEntity.getUpdatedAt(),
                legacyUserEntity.getDeletedAt()
        );
    }
}