package com.investlee.domain.user.event;

import com.investlee.domain.user.UserEntity;

import java.time.LocalDateTime;

public class UserNameUpdate extends UserEvent {
    public UserNameUpdate(UserEntity userEntity) {
        super(userEntity);
    }

    @Override
    public LocalDateTime occurredOn() {
        return userEntity.getUpdatedAt();
    }
}
