package com.investlee.api.legacy.group;

import com.investlee.api.legacy.LegacyConverter;
import com.investlee.domain.advance.campaign.AdvanceCampaignEntity;
import com.investlee.domain.legacy.campaign.LegacyCampaignEntity;
import com.investlee.domain.legacy.campaign.LegacyCampaignRepository;
import com.investlee.domain.legacy.group.LegacyGroupEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LegacyGroupConverter implements LegacyConverter<
        LegacyGroupEntity,
        AdvanceCampaignEntity
        > {

    private final LegacyCampaignRepository legacyCampaignRepository;

    public AdvanceCampaignEntity convert(LegacyGroupEntity LegacyGroupEntity) {
        LegacyCampaignEntity legacyCampaignEntity
                = legacyCampaignRepository.findById(LegacyGroupEntity.getCampaignId())
                .orElseThrow(EntityNotFoundException::new);
        return AdvanceCampaignEntity.migrated(
                LegacyGroupEntity.getId(),
                legacyCampaignEntity.getName() + "-" + LegacyGroupEntity.getName(),
                legacyCampaignEntity.getUserId(),
                legacyCampaignEntity.getBudget(),
                LegacyGroupEntity.getLinkUrl(),
                LegacyGroupEntity.getCreatedAt(),
                LegacyGroupEntity.getUpdatedAt(),
                LegacyGroupEntity.getDeletedAt()
        );
    }
}