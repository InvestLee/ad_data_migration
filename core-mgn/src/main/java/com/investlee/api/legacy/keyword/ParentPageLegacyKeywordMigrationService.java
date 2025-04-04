package com.investlee.api.legacy.keyword;

import com.investlee.api.legacy.ParentPageLegacyMigrationService;
import com.investlee.domain.advance.keyword.AdvanceKeywordEntity;
import com.investlee.domain.legacy.group.LegacyGroupEntity;
import com.investlee.domain.legacy.group.LegacyGroupRepository;
import com.investlee.domain.legacy.keyword.LegacyKeywordEntity;
import com.investlee.domain.legacy.keyword.LegacyKeywordRepository;
import com.investlee.domain.migration.keyword.KeywordPageMigration;
import com.investlee.domain.migration.keyword.KeywordPageMigrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ParentPageLegacyKeywordMigrationService extends
        ParentPageLegacyMigrationService<
                KeywordPageMigration,
                LegacyKeywordEntity,
                AdvanceKeywordEntity,
                LegacyGroupEntity> {

    public ParentPageLegacyKeywordMigrationService(
            KeywordPageMigrationRepository keywordMigrationRepository,
            LegacyKeywordRepository legacyKeywordRepository,
            LegacyKeywordMigrationService legacyKeywordMigrationService,
            LegacyGroupRepository legacyGroupRepository) {
        super(
                keywordMigrationRepository,
                legacyKeywordRepository,
                legacyKeywordMigrationService,
                legacyGroupRepository
        );
    }

    @Override
    protected KeywordPageMigration firstPageMigration(
            Long userId,
            boolean isSuccess,
            Page<LegacyKeywordEntity> legacyPage) {
        return KeywordPageMigration.first(
                isSuccess,
                userId,
                PARENT_PAGE_SIZE,
                legacyPage.getTotalElements()
        );
    }

    @Override
    protected List<LegacyKeywordEntity> findAllByParentIdsIn(List<Long> legacyParentIds) {
        return ((LegacyKeywordRepository) legacyPageableRepository)
                .findAllByGroupIdInAndDeletedAtIsNull(legacyParentIds);
    }
}