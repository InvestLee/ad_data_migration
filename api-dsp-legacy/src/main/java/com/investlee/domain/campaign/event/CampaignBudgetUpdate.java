package com.investlee.domain.campaign.event;

import com.investlee.domain.campaign.CampaignEntity;

import java.time.LocalDateTime;

public class CampaignBudgetUpdate extends CampaignEvent {
    public CampaignBudgetUpdate(CampaignEntity campaignEntity) {

        super(campaignEntity);
    }

    @Override
    public LocalDateTime occurredOn() {

        return campaignEntity.getUpdatedAt();
    }
}
