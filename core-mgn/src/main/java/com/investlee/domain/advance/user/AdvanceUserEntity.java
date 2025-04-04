package com.investlee.domain.advance.user;

import com.investlee.domain.advance.AdvanceBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class AdvanceUserEntity implements AdvanceBaseEntity {

    @Id
    private Long id;

    private String name;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private LocalDateTime migratedAt;

    public AdvanceUserEntity(
            Long id,
            String name,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt,
            LocalDateTime migratedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.migratedAt = migratedAt;
    }

    public static AdvanceUserEntity migrated(
            Long id,
            String name,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt) {
        return new AdvanceUserEntity(
                id,
                name,
                createdAt,
                updatedAt,
                deletedAt,
                LocalDateTime.now());
    }
}
