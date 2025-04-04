package com.investlee.domain.user;

import com.investlee.domain.user.event.LegacyLegacyUserCreate;
import com.investlee.domain.user.event.LegacyLegacyUserDelete;
import com.investlee.domain.user.event.LegacyLegacyUserNameUpdate;
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
public class LegacyUserEntity extends AbstractAggregateRoot<LegacyUserEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private LegacyUserEntity(String name, LocalDateTime createdAt) {
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        this.deletedAt = null;
        registerEvent(new LegacyLegacyUserCreate(this));
    }

    public static LegacyUserEntity of(String name) {
        return new LegacyUserEntity(name, LocalDateTime.now());
    }

    public void updateName(String name) {
        this.name = name;
        updatedAt = LocalDateTime.now();
        registerEvent(new LegacyLegacyUserNameUpdate(this));
    }

    public void delete() {
        deletedAt = LocalDateTime.now();
        registerEvent(new LegacyLegacyUserDelete(this));
    }
}
