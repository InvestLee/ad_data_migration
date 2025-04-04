package com.investlee.domain.campaign.event;

import com.investlee.domain.campaign.LegacyCampaignEntity;

import java.time.LocalDateTime;

public class LegacyCampaignCreate extends LegacyCampaignEvent {
    public LegacyCampaignCreate(LegacyCampaignEntity legacyCampaignEntity) {
        super(legacyCampaignEntity);
    }

    @Override
    public LocalDateTime occurredOn() {
        return legacyCampaignEntity.getCreatedAt();
    }
}
