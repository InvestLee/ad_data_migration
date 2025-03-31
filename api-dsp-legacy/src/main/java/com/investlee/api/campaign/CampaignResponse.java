package com.investlee.api.campaign;

import com.investlee.core.campaign.CampaignResult;

import java.time.LocalDateTime;

public record CampaignResponse(
        Long id,
        String name,
        Long userId,
        Long budget,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt) {
    public static CampaignResponse from(CampaignResult campaignResult) {
        return new CampaignResponse(
                campaignResult.id(),
                campaignResult.name(),
                campaignResult.userId(),
                campaignResult.budget(),
                campaignResult.createdAt(),
                campaignResult.updatedAt(),
                campaignResult.deletedAt());
    }
}
