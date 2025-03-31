package com.investlee.api.keyword;

import com.investlee.core.keyword.KeywordResult;

import java.time.LocalDateTime;

public record KeywordResponse(
        Long id,
        String text,
        Long adGroupId,
        LocalDateTime createdAt,
        LocalDateTime deletedAt) {
    public static KeywordResponse from(KeywordResult keyword) {
        return new KeywordResponse(
                keyword.id(),
                keyword.text(),
                keyword.groupId(),
                keyword.createdAt(),
                keyword.deletedAt());
    }
}
