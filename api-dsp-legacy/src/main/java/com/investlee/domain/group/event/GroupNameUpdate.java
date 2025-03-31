package com.investlee.domain.group.event;

import com.investlee.domain.group.GroupEntity;

import java.time.LocalDateTime;

public class GroupNameUpdate extends GroupEvent {
    public GroupNameUpdate(GroupEntity groupEntity) {
        super(groupEntity);
    }

    @Override
    public LocalDateTime occurredOn() {
        return groupEntity.getUpdatedAt();
    }
}
