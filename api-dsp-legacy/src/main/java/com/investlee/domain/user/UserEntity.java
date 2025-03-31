package com.investlee.domain.user;

import com.investlee.domain.user.event.UserCreate;
import com.investlee.domain.user.event.UserDelete;
import com.investlee.domain.user.event.UserNameUpdate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class UserEntity extends AbstractAggregateRoot<UserEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private UserEntity(String name, LocalDateTime createdAt) {
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        this.deletedAt = null;
        registerEvent(new UserCreate(this));
    }

    public static UserEntity of(String name) {
        return new UserEntity(name, LocalDateTime.now());
    }

    public void updateName(String name) {
        this.name = name;
        updatedAt = LocalDateTime.now();
        registerEvent(new UserNameUpdate(this));
    }

    public void delete() {
        deletedAt = LocalDateTime.now();
        registerEvent(new UserDelete(this));
    }
}
