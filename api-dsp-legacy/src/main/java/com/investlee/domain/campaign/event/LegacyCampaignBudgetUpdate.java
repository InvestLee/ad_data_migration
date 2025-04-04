package com.investlee.domain.campaign.event;

import com.investlee.domain.campaign.LegacyCampaignEntity;

import java.time.LocalDateTime;

public class LegacyCampaignBudgetUpdate extends LegacyCampaignEvent {
    public LegacyCampaignBudgetUpdate(LegacyCampaignEntity legacyCampaignEntity) {

        super(legacyCampaignEntity);
    }

    @Override
    public LocalDateTime occurredOn() {

        return legacyCampaignEntity.getUpdatedAt();
    }
}
