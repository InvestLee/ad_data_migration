package com.investlee.core.campaign;

import com.investlee.domain.campaign.CampaignEntity;

import java.time.LocalDateTime;

public record CampaignResult(
        Long id,
        String name,
        Long userId,
        Long budget,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt) {
    public static CampaignResult from(CampaignEntity campaignEntity) {
        return new CampaignResult(
                campaignEntity.getId(),
                campaignEntity.getName(),
                campaignEntity.getUserId(),
                campaignEntity.getBudget(),
                campaignEntity.getCreatedAt(),
                campaignEntity.getUpdatedAt(),
                campaignEntity.getDeletedAt());
    }
}
