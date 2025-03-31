package com.investlee.core.group;

import com.investlee.domain.group.GroupEntity;

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
    public static GroupResult from(GroupEntity groupEntity) {
        return new GroupResult(
                groupEntity.getId(),
                groupEntity.getName(),
                groupEntity.getCampaignId(),
                groupEntity.getUserId(),
                groupEntity.getLinkUrl(),
                groupEntity.getCreatedAt(),
                groupEntity.getUpdatedAt(),
                groupEntity.getDeletedAt());
    }
}
