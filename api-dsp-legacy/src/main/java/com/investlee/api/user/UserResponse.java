package com.investlee.api.user;

import com.investlee.core.user.UserResult;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt) {

    public static UserResponse from(UserResult user) {
        return new UserResponse(
                user.id(),
                user.name(),
                user.createdAt(),
                user.updatedAt(),
                user.deletedAt());
    }
}
