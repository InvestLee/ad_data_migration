package com.investlee.core.keyword;

import com.investlee.domain.keyword.LegacyKeywordEntity;

import java.time.LocalDateTime;

public record KeywordResult(
        Long id,
        String text,
        Long groupId,
        Long userId,
        LocalDateTime createdAt,
        LocalDateTime deletedAt) {
    public static KeywordResult from(LegacyKeywordEntity legacyKeywordEntity) {
        return new KeywordResult(
                legacyKeywordEntity.getId(),
                legacyKeywordEntity.getText(),
                legacyKeywordEntity.getGroupId(),
                legacyKeywordEntity.getUserId(),
                legacyKeywordEntity.getCreatedAt(),
                legacyKeywordEntity.getDeletedAt());
    }
}
