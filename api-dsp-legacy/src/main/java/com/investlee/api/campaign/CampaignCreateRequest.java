package com.investlee.api.campaign;

public record CampaignCreateRequest(String name, Long userId, Long budget) {
}
