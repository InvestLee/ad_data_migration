package com.investlee.domain.group.event;

import com.investlee.domain.AggregateType;
import com.investlee.domain.DomainEvent;
import com.investlee.domain.group.GroupEntity;

import java.time.LocalDateTime;

public abstract class GroupEvent implements DomainEvent {

    protected GroupEntity groupEntity;

    public GroupEvent(GroupEntity groupEntity) {
        this.groupEntity = groupEntity;
    }

    @Override
    public AggregateType aggregateType() {
        return AggregateType.GROUP;
    }

    @Override
    public Long aggregateId() {
        return groupEntity.getId();
    }

    @Override
    public Long ownerId() {
        return groupEntity.getUserId();
    }

    @Override
    public abstract LocalDateTime occurredOn();

}