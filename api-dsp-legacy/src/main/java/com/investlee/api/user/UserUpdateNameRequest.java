package com.investlee.api.user;

public record UserUpdateNameRequest(
        Long id,
        String name) {
}
