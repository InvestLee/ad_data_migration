package com.investlee.domain.legacy.keyword;

import com.investlee.domain.legacy.LegacyPageableRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LegacyKeywordRepository extends LegacyPageableRepository<LegacyKeywordEntity> {
    List<LegacyKeywordEntity> findAllByGroupIdInAndDeletedAtIsNull(List<Long> groupIds);

}
