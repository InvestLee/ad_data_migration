package com.investlee.domain.campaign.event;

import com.investlee.domain.campaign.CampaignEntity;

import java.time.LocalDateTime;

public class CampaignCreate extends CampaignEvent {
    public CampaignCreate(CampaignEntity campaignEntity) {
        super(campaignEntity);
    }

    @Override
    public LocalDateTime occurredOn() {
        return campaignEntity.getCreatedAt();
    }
}
