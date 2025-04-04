package com.investlee.domain.advance.campaign;

import com.investlee.domain.advance.AdvanceBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class AdvanceCampaignEntity implements AdvanceBaseEntity {

    @Id
    private Long id;

    private String name;
    private Long userId;
    private Long budget;
    private String linkUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private LocalDateTime migratedAt;

    public AdvanceCampaignEntity(
            Long id,
            String name,
            Long userId,
            Long budget,
            String linkUrl,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt,
            LocalDateTime migratedAt
    ) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.budget = budget;
        this.linkUrl = linkUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.migratedAt = migratedAt;
    }

    public static AdvanceCampaignEntity migrated(
            Long id,
            String name,
            Long userId,
            Long budget,
            String linkUrl,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt
    ) {
        return new AdvanceCampaignEntity(
                id,
                name,
                userId,
                budget,
                linkUrl,
                createdAt,
                updatedAt,
                deletedAt,
                LocalDateTime.now()
        );
    }
}
