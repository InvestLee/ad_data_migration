package com.investlee.domain.migration.user;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class MigrationUserEntity extends AbstractAggregateRoot<MigrationUserEntity> {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private MigrationUserStatus status;
    private LocalDateTime agreedAt;
    private LocalDateTime updateAt;
    @Enumerated(EnumType.STRING)
    private MigrationUserStatus prevStatus;

    private MigrationUserEntity(Long id, LocalDateTime agreedAt) {
        this.id = id;
        this.status = MigrationUserStatus.AGREED;
        this.agreedAt = agreedAt;
        this.updateAt = agreedAt;
        registerEvent(new MigrationAgreedEvent(this));
    }

    public static MigrationUserEntity agreed(Long id) {
        return new MigrationUserEntity(id, LocalDateTime.now());
    }

    public void progressMigration() {
        if (MigrationUserStatus.RETRIED.equals(status)) {
            status = prevStatus.next();
        } else {
            prevStatus = status;
            status = status.next();
        }
        updateAt = LocalDateTime.now();
        registerEvent(new MigrationProgressedEvent(this));
    }

    public void retry() {
        if (!MigrationUserStatus.RETRIED.equals(status)) {
            prevStatus = status;
            status = MigrationUserStatus.RETRIED;
        }
        updateAt = LocalDateTime.now();
        registerEvent(new MigrationRetriedEvent(this));
    }
}