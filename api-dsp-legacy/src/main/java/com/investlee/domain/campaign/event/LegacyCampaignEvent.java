package com.investlee.domain.campaign.event;

import com.investlee.domain.AggregateType;
import com.investlee.domain.DomainEvent;
import com.investlee.domain.campaign.LegacyCampaignEntity;

import java.time.LocalDateTime;

public abstract class LegacyCampaignEvent implements DomainEvent {

    protected LegacyCampaignEntity legacyCampaignEntity;

    public LegacyCampaignEvent(LegacyCampaignEntity legacyCampaignEntity) {

        this.legacyCampaignEntity = legacyCampaignEntity;
    }

    @Override
    public AggregateType aggregateType() {

        return AggregateType.CAMPAIGN;
    }

    @Override
    public Long aggregateId() {

        return legacyCampaignEntity.getId();
    }

    @Override
    public Long ownerId() {

        return legacyCampaignEntity.getUserId();
    }

    @Override
    public abstract LocalDateTime occurredOn();

}