package com.investlee.core.keyword;

import com.investlee.domain.keyword.KeywordEntity;

import java.time.LocalDateTime;

public record KeywordResult(
        Long id,
        String text,
        Long groupId,
        Long userId,
        LocalDateTime createdAt,
        LocalDateTime deletedAt) {
    public static KeywordResult from(KeywordEntity keywordEntity) {
        return new KeywordResult(
                keywordEntity.getId(),
                keywordEntity.getText(),
                keywordEntity.getGroupId(),
                keywordEntity.getUserId(),
                keywordEntity.getCreatedAt(),
                keywordEntity.getDeletedAt());
    }
}
