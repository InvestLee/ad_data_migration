package com.investlee.api.legacy;

public record PageMigrationResult(
        Long userId,
        int pageNumber,
        int totalPages,
        Long totalCount,
        boolean isSuccess) {
}