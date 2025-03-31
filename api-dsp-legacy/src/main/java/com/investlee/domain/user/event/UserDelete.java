package com.investlee.domain.user.event;

import com.investlee.domain.user.UserEntity;

import java.time.LocalDateTime;

public class UserDelete extends UserEvent {
    public UserDelete(UserEntity userEntity) {
        super(userEntity);
    }

    @Override
    public LocalDateTime occurredOn() {
        return userEntity.getDeletedAt();
    }
}
