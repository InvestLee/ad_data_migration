package com.investlee.domain.group.event;

import com.investlee.domain.group.GroupEntity;

import java.time.LocalDateTime;

public class GroupDelete extends GroupEvent {
    public GroupDelete(GroupEntity groupEntity) {
        super(groupEntity);
    }

    @Override
    public LocalDateTime occurredOn() {
        return groupEntity.getDeletedAt();
    }
}
