package com.investlee.domain.campaign;

import com.investlee.domain.campaign.event.LegacyCampaignBudgetUpdate;
import com.investlee.domain.campaign.event.LegacyCampaignCreate;
import com.investlee.domain.campaign.event.LegacyCampaignDelete;
import com.investlee.domain.campaign.event.LegacyCampaignNameUpdate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class LegacyCampaignEntity extends AbstractAggregateRoot<LegacyCampaignEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long userId;
    private Long budget;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    private LegacyCampaignEntity(
            String name,
            Long userId,
            Long budget,
            LocalDateTime createdAt) {
        this.name = name;
        this.userId = userId;
        this.budget = budget;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        this.deletedAt = null;
        registerEvent(new LegacyCampaignCreate(this));
    }

    public static LegacyCampaignEntity of(
            String name,
            Long userId,
            Long budget) {
        return new LegacyCampaignEntity(
                name,
                userId,
                budget,
                LocalDateTime.now());
    }

    public void updateName(String name) {
        this.name = name;
        updatedAt = LocalDateTime.now();
        registerEvent(new LegacyCampaignNameUpdate(this));
    }

    public void updateBudget(Long budget) {
        this.budget = budget;
        updatedAt = LocalDateTime.now();
        registerEvent(new LegacyCampaignBudgetUpdate(this));
    }

    public void delete() {
        deletedAt = LocalDateTime.now();
        registerEvent(new LegacyCampaignDelete(this));
    }

}
