package com.investlee.api.campaign;

public record CampaignUpdateBudgetRequest(
        Long id,
        Long budget
) {
}
