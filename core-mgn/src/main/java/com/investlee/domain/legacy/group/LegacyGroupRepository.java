package com.investlee.domain.legacy.group;

import com.investlee.domain.legacy.LegacyPageableRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LegacyGroupRepository extends LegacyPageableRepository<LegacyGroupEntity> {

    List<LegacyGroupEntity> findAllByCampaignIdAndDeletedAtIsNull(Long campaignId);

    List<LegacyGroupEntity> findAllByCampaignIdInAndDeletedAtIsNull(List<Long> campaignIds);
}
