package com.investlee.core.campaign;

public record CampaignCreateCommand(
        String name,
        Long userId,
        Long budget) {
}
