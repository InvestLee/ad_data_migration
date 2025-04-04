package com.investlee.core.group;

import com.investlee.domain.group.LegacyGroupEntity;

import java.time.LocalDateTime;

public record GroupResult(
        Long id,
        String name,
        Long campaignId,
        Long userId,
        String linkUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt) {
    public static GroupResult from(LegacyGroupEntity legacyGroupEntity) {
        return new GroupResult(
                legacyGroupEntity.getId(),
                legacyGroupEntity.getName(),
                legacyGroupEntity.getCampaignId(),
                legacyGroupEntity.getUserId(),
                legacyGroupEntity.getLinkUrl(),
                legacyGroupEntity.getCreatedAt(),
                legacyGroupEntity.getUpdatedAt(),
                legacyGroupEntity.getDeletedAt());
    }
}
