package com.investlee.api.legacy.group;

import com.investlee.api.legacy.PageLegacyMigrationService;
import com.investlee.domain.advance.campaign.AdvanceCampaignEntity;
import com.investlee.domain.legacy.group.LegacyGroupEntity;
import com.investlee.domain.legacy.group.LegacyGroupRepository;
import com.investlee.domain.migration.group.GroupPageMigration;
import com.investlee.domain.migration.group.GroupPageMigrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PageLegacyGroupMigrationService extends
        PageLegacyMigrationService<
                GroupPageMigration,
                LegacyGroupEntity,
                AdvanceCampaignEntity> {

    public PageLegacyGroupMigrationService(
            GroupPageMigrationRepository GroupMigrationRepository,
            LegacyGroupRepository legacyGroupRepository,
            LegacyGroupMigrationService legacyGroupMigrationService) {
        super(
                GroupMigrationRepository,
                legacyGroupRepository,
                legacyGroupMigrationService
        );
    }

    @Override
    protected GroupPageMigration firstPageMigration(
            Long userId,
            boolean isSuccess,
            Page<LegacyGroupEntity> legacyPage) {
        return GroupPageMigration.first(
                isSuccess, userId,
                PAGE_SIZE,
                legacyPage.getTotalElements()
        );
    }
}
