package com.investlee.domain.advance.keyword;

import com.investlee.domain.advance.AdvanceBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class AdvanceKeywordEntity implements AdvanceBaseEntity {

    @Id
    private Long id;

    private String text;
    private Long campaignId;
    private Long userId;

    @CreatedDate
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private LocalDateTime migratedAt;

    public AdvanceKeywordEntity(
            Long id,
            String text,
            Long campaignId,
            Long userId,
            LocalDateTime createdAt,
            LocalDateTime deletedAt,
            LocalDateTime migratedAt
    ) {
        this.id = id;
        this.text = text;
        this.campaignId = campaignId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
        this.migratedAt = migratedAt;
    }

    public static AdvanceKeywordEntity migrated(
            Long id,
            String text,
            Long campaignId,
            Long userId,
            LocalDateTime createdAt,
            LocalDateTime deletedAt
    ) {
        return new AdvanceKeywordEntity(
                id,
                text,
                campaignId,
                userId,
                createdAt,
                deletedAt,
                LocalDateTime.now());
    }
}
