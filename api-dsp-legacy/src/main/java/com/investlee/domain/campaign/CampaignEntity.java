package com.investlee.domain.campaign;

import com.investlee.domain.campaign.event.CampaignBudgetUpdate;
import com.investlee.domain.campaign.event.CampaignCreate;
import com.investlee.domain.campaign.event.CampaignDelete;
import com.investlee.domain.campaign.event.CampaignNameUpdate;
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
public class CampaignEntity extends AbstractAggregateRoot<CampaignEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long userId;
    private Long budget;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    private CampaignEntity(
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
        registerEvent(new CampaignCreate(this));
    }

    public static CampaignEntity of(
            String name,
            Long userId,
            Long budget) {
        return new CampaignEntity(
                name,
                userId,
                budget,
                LocalDateTime.now());
    }

    public void updateName(String name) {
        this.name = name;
        updatedAt = LocalDateTime.now();
        registerEvent(new CampaignNameUpdate(this));
    }

    public void updateBudget(Long budget) {
        this.budget = budget;
        updatedAt = LocalDateTime.now();
        registerEvent(new CampaignBudgetUpdate(this));
    }

    public void delete() {
        deletedAt = LocalDateTime.now();
        registerEvent(new CampaignDelete(this));
    }

}
