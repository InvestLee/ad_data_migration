package com.investlee.core.user;

import com.investlee.domain.user.LegacyUserEntity;

import java.time.LocalDateTime;

public record UserResult(
        Long id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt) {
    public static UserResult from(LegacyUserEntity legacyUserEntity) {
        return new UserResult(
                legacyUserEntity.getId(),
                legacyUserEntity.getName(),
                legacyUserEntity.getCreatedAt(),
                legacyUserEntity.getUpdatedAt(),
                legacyUserEntity.getDeletedAt());
    }
}
