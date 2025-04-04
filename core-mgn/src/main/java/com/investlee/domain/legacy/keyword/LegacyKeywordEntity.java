package com.investlee.domain.legacy.keyword;

import com.investlee.domain.legacy.LegacyBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class LegacyKeywordEntity implements LegacyBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private Long groupId;
    private Long userId;

    @CreatedDate
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
