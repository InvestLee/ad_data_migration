package com.investlee.core.campaign;

import com.investlee.domain.campaign.LegacyCampaignEntity;

import java.time.LocalDateTime;

public record CampaignResult(
        Long id,
        String name,
        Long userId,
        Long budget,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt) {
    public static CampaignResult from(LegacyCampaignEntity legacyCampaignEntity) {
        return new CampaignResult(
                legacyCampaignEntity.getId(),
                legacyCampaignEntity.getName(),
                legacyCampaignEntity.getUserId(),
                legacyCampaignEntity.getBudget(),
                legacyCampaignEntity.getCreatedAt(),
                legacyCampaignEntity.getUpdatedAt(),
                legacyCampaignEntity.getDeletedAt());
    }
}
