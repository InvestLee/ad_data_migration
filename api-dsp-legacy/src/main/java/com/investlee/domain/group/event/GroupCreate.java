package com.investlee.domain.group.event;

import com.investlee.domain.group.GroupEntity;

import java.time.LocalDateTime;

public class GroupCreate extends GroupEvent {
    public GroupCreate(GroupEntity groupEntity) {
        super(groupEntity);
    }

    @Override
    public LocalDateTime occurredOn() {
        return groupEntity.getCreatedAt();
    }
}
