package com.investlee.core.user;

import com.investlee.domain.user.UserEntity;

import java.time.LocalDateTime;

public record UserResult(
        Long id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt) {
    public static UserResult from(UserEntity userEntity) {
        return new UserResult(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getCreatedAt(),
                userEntity.getUpdatedAt(),
                userEntity.getDeletedAt());
    }
}
