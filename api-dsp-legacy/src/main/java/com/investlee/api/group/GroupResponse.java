package com.investlee.api.group;

import com.investlee.core.group.GroupResult;

import java.time.LocalDateTime;

public record GroupResponse(
        Long id,
        String name,
        Long campaignId,
        String linkUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt
) {
    public static GroupResponse from(GroupResult groupResult) {
        return new GroupResponse(
                groupResult.id(),
                groupResult.name(),
                groupResult.campaignId(),
                groupResult.linkUrl(),
                groupResult.createdAt(),
                groupResult.updatedAt(),
                groupResult.deletedAt());
    }
}
