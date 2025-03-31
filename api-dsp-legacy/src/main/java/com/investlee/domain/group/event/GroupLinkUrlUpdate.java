package com.investlee.domain.group.event;

import com.investlee.domain.group.GroupEntity;

import java.time.LocalDateTime;

public class GroupLinkUrlUpdate extends GroupEvent {
    public GroupLinkUrlUpdate(GroupEntity groupEntity) {
        super(groupEntity);
    }

    @Override
    public LocalDateTime occurredOn() {
        return groupEntity.getUpdatedAt();
    }
}
