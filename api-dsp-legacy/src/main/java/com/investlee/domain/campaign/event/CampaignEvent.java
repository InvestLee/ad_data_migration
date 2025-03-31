package com.investlee.domain.campaign.event;

import com.investlee.domain.AggregateType;
import com.investlee.domain.DomainEvent;
import com.investlee.domain.campaign.CampaignEntity;

import java.time.LocalDateTime;

public abstract class CampaignEvent implements DomainEvent {

    protected CampaignEntity campaignEntity;

    public CampaignEvent(CampaignEntity campaignEntity) {

        this.campaignEntity = campaignEntity;
    }

    @Override
    public AggregateType aggregateType() {

        return AggregateType.CAMPAIGN;
    }

    @Override
    public Long aggregateId() {

        return campaignEntity.getId();
    }

    @Override
    public Long ownerId() {

        return campaignEntity.getUserId();
    }

    @Override
    public abstract LocalDateTime occurredOn();

}