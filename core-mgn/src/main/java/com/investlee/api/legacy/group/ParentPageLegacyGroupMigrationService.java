package com.investlee.api.legacy.group;

import com.investlee.api.legacy.ParentPageLegacyMigrationService;
import com.investlee.domain.advance.campaign.AdvanceCampaignEntity;
import com.investlee.domain.legacy.campaign.LegacyCampaignEntity;
import com.investlee.domain.legacy.campaign.LegacyCampaignRepository;
import com.investlee.domain.legacy.group.LegacyGroupEntity;
import com.investlee.domain.legacy.group.LegacyGroupRepository;
import com.investlee.domain.migration.group.GroupPageMigration;
import com.investlee.domain.migration.group.GroupPageMigrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ParentPageLegacyGroupMigrationService extends
        ParentPageLegacyMigrationService<
                GroupPageMigration,
                LegacyGroupEntity,
                AdvanceCampaignEntity,
                LegacyCampaignEntity
                > {

    public ParentPageLegacyGroupMigrationService(
            GroupPageMigrationRepository GroupMigrationRepository,
            LegacyGroupRepository legacyGroupRepository,
            LegacyGroupMigrationService legacyGroupMigrationService,
            LegacyCampaignRepository legacyCampaignRepository) {
        super(
                GroupMigrationRepository,
                legacyGroupRepository,
                legacyGroupMigrationService,
                legacyCampaignRepository
        );
    }

    @Override
    protected GroupPageMigration firstPageMigration(
            Long userId,
            boolean isSuccess,
            Page<LegacyGroupEntity> legacyPage) {
        return GroupPageMigration.first(
                isSuccess,
                userId,
                PARENT_PAGE_SIZE,
                legacyPage.getTotalElements()
        );
    }

    @Override
    protected List<LegacyGroupEntity> findAllByParentIdsIn(List<Long> legacyParentIds) {
        return ((LegacyGroupRepository) legacyPageableRepository)
                .findAllByCampaignIdInAndDeletedAtIsNull(legacyParentIds);
    }
}