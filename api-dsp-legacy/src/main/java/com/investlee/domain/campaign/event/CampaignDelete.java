package com.investlee.domain.campaign.event;

import com.investlee.domain.campaign.CampaignEntity;

import java.time.LocalDateTime;

public class CampaignDelete extends CampaignEvent {
    public CampaignDelete(CampaignEntity campaignEntity) {
        super(campaignEntity);
    }

    @Override
    public LocalDateTime occurredOn() {
        return campaignEntity.getDeletedAt();
    }
}
