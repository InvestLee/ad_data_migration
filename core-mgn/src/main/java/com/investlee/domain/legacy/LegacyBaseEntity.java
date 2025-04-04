package com.investlee.domain.legacy;

import java.time.LocalDateTime;
import java.util.Optional;

public interface LegacyBaseEntity {
    default boolean isDeleted() {
        return Optional.ofNullable(getDeletedAt()).isPresent();
    }

    LocalDateTime getDeletedAt();

    Long getId();
}
