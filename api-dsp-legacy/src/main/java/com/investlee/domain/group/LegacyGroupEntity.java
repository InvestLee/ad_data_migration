package com.investlee.domain.group;

import com.investlee.domain.group.event.LegacyGroupCreate;
import com.investlee.domain.group.event.LegacyGroupDelete;
import com.investlee.domain.group.event.LegacyGroupLinkUrlUpdate;
import com.investlee.domain.group.event.LegacyGroupNameUpdate;
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
public class LegacyGroupEntity extends AbstractAggregateRoot<LegacyGroupEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long campaignId;
    private Long userId;
    private String linkUrl;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private LegacyGroupEntity(
            String name,
            Long campaignId,
            Long userId,
            String linkUrl,
            LocalDateTime createdAt) {
        this.name = name;
        this.campaignId = campaignId;
        this.userId = userId;
        this.linkUrl = linkUrl;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        this.deletedAt = null;
        registerEvent(new LegacyGroupCreate(this));
    }

    public static LegacyGroupEntity of(
            String name,
            Long campaignId,
            Long userId,
            String linkUrl) {
        return new LegacyGroupEntity(
                name,
                campaignId,
                userId, linkUrl,
                LocalDateTime.now()
        );
    }

    public void updateName(String name) {
        this.name = name;
        updatedAt = LocalDateTime.now();
        registerEvent(new LegacyGroupNameUpdate(this));
    }

    public void updateLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
        updatedAt = LocalDateTime.now();
        registerEvent(new LegacyGroupLinkUrlUpdate(this));
    }

    public void delete() {
        deletedAt = LocalDateTime.now();
        registerEvent(new LegacyGroupDelete(this));
    }
}
