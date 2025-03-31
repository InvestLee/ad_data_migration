package com.investlee.domain.user.event;

import com.investlee.domain.user.UserEntity;

import java.time.LocalDateTime;

public class UserCreate extends UserEvent {
    public UserCreate(UserEntity userEntity) {
        super(userEntity);
    }

    @Override
    public LocalDateTime occurredOn() {
        return userEntity.getCreatedAt();
    }
}
