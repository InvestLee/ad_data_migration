package com.investlee.domain.campaign.event;

import com.investlee.domain.campaign.LegacyCampaignEntity;

import java.time.LocalDateTime;

public class LegacyCampaignNameUpdate extends LegacyCampaignEvent {
    public LegacyCampaignNameUpdate(LegacyCampaignEntity legacyCampaignEntity) {
        super(legacyCampaignEntity);
    }

    @Override
    public LocalDateTime occurredOn() {
        return legacyCampaignEntity.getUpdatedAt();
    }
}
