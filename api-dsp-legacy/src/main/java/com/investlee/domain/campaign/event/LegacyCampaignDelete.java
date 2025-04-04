package com.investlee.domain.campaign.event;

import com.investlee.domain.campaign.LegacyCampaignEntity;

import java.time.LocalDateTime;

public class LegacyCampaignDelete extends LegacyCampaignEvent {
    public LegacyCampaignDelete(LegacyCampaignEntity legacyCampaignEntity) {
        super(legacyCampaignEntity);
    }

    @Override
    public LocalDateTime occurredOn() {
        return legacyCampaignEntity.getDeletedAt();
    }
}
