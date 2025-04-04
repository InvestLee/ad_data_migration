package com.investlee.api.legacy.group;

import com.investlee.api.legacy.LegacyMigrationService;
import com.investlee.domain.advance.campaign.AdvanceCampaignEntity;
import com.investlee.domain.advance.campaign.AdvanceCampaignRepository;
import com.investlee.domain.legacy.group.LegacyGroupEntity;
import com.investlee.domain.legacy.group.LegacyGroupRepository;
import org.springframework.stereotype.Service;

@Service
public class LegacyGroupMigrationService extends
        LegacyMigrationService<
                LegacyGroupEntity,
                AdvanceCampaignEntity
                > {

    public LegacyGroupMigrationService(LegacyGroupRepository legacyGroupRepository,
                                       AdvanceCampaignRepository advanceCampaignRepository,
                                       LegacyGroupConverter legacyGroupConverter) {
        super(
                legacyGroupRepository,
                advanceCampaignRepository,
                legacyGroupConverter
        );
    }
}