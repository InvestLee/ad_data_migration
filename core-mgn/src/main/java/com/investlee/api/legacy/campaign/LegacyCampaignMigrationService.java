package com.investlee.api.legacy.campaign;

import com.investlee.api.legacy.LegacyMigrationService;
import com.investlee.api.legacy.group.LegacyGroupMigrationService;
import com.investlee.domain.advance.campaign.AdvanceCampaignEntity;
import com.investlee.domain.legacy.campaign.LegacyCampaignEntity;
import com.investlee.domain.legacy.group.LegacyGroupEntity;
import com.investlee.domain.legacy.group.LegacyGroupRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class LegacyCampaignMigrationService extends
        LegacyMigrationService<
                LegacyCampaignEntity,
                AdvanceCampaignEntity> {

    private final LegacyGroupMigrationService legacyGroupMigrationService;
    private final LegacyGroupRepository legacyGroupRepository;

    public LegacyCampaignMigrationService(
            CrudRepository<LegacyCampaignEntity, Long> legacyRepository,
            CrudRepository<AdvanceCampaignEntity, Long> advanceRepository,
            LegacyGroupMigrationService legacyGroupMigrationService,
            LegacyGroupRepository legacyGroupRepository) {
        super(
                legacyRepository,
                advanceRepository,
                null
        );
        this.legacyGroupMigrationService = legacyGroupMigrationService;
        this.legacyGroupRepository = legacyGroupRepository;
    }

    @Override
    public void migrate(LegacyCampaignEntity legacyCampaignEntity) {
        for (LegacyGroupEntity legacyGroupEntity
                : legacyGroupRepository.findAllByCampaignIdAndDeletedAtIsNull(
                legacyCampaignEntity.getId())
        ) {
            legacyGroupMigrationService.migrate(legacyGroupEntity.getId());
        }
    }
}