package com.investlee.domain.group;

import com.investlee.domain.group.event.GroupCreate;
import com.investlee.domain.group.event.GroupDelete;
import com.investlee.domain.group.event.GroupLinkUrlUpdate;
import com.investlee.domain.group.event.GroupNameUpdate;
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
public class GroupEntity extends AbstractAggregateRoot<GroupEntity> {

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

    private GroupEntity(
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
        registerEvent(new GroupCreate(this));
    }

    public static GroupEntity of(
            String name,
            Long campaignId,
            Long userId,
            String linkUrl) {
        return new GroupEntity(
                name,
                campaignId,
                userId, linkUrl,
                LocalDateTime.now()
        );
    }

    public void updateName(String name) {
        this.name = name;
        updatedAt = LocalDateTime.now();
        registerEvent(new GroupNameUpdate(this));
    }

    public void updateLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
        updatedAt = LocalDateTime.now();
        registerEvent(new GroupLinkUrlUpdate(this));
    }

    public void delete() {
        deletedAt = LocalDateTime.now();
        registerEvent(new GroupDelete(this));
    }
}
