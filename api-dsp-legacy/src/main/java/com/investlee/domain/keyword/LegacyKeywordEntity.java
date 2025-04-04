package com.investlee.domain.keyword;

import com.investlee.domain.keyword.event.LegacyKeywordCreate;
import com.investlee.domain.keyword.event.LegacyKeywordDelete;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class LegacyKeywordEntity extends AbstractAggregateRoot<LegacyKeywordEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private Long groupId;
    private Long userId;

    @CreatedDate
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;

    private LegacyKeywordEntity(
            String text,
            Long groupId,
            Long userId,
            LocalDateTime createdAt,
            LocalDateTime deletedAt) {
        this.text = text;
        this.groupId = groupId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
        registerEvent(new LegacyKeywordCreate(this));
    }

    public static LegacyKeywordEntity of(
            String text,
            Long groupId,
            Long userId) {
        return new LegacyKeywordEntity(
                text,
                groupId,
                userId,
                LocalDateTime.now(),
                null);
    }

    public void delete() {
        deletedAt = LocalDateTime.now();
        registerEvent(new LegacyKeywordDelete(this));
    }
}
