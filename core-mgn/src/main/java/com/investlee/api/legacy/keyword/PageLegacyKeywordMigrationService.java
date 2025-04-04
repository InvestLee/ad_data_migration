package com.investlee.api.legacy.keyword;

import com.investlee.api.legacy.PageLegacyMigrationService;
import com.investlee.domain.advance.keyword.AdvanceKeywordEntity;
import com.investlee.domain.legacy.keyword.LegacyKeywordEntity;
import com.investlee.domain.legacy.keyword.LegacyKeywordRepository;
import com.investlee.domain.migration.keyword.KeywordPageMigration;
import com.investlee.domain.migration.keyword.KeywordPageMigrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PageLegacyKeywordMigrationService extends
        PageLegacyMigrationService<
                KeywordPageMigration,
                LegacyKeywordEntity,
                AdvanceKeywordEntity
                > {

    public PageLegacyKeywordMigrationService(
            KeywordPageMigrationRepository keywordMigrationRepository,
            LegacyKeywordRepository legacyKeywordRepository,
            LegacyKeywordMigrationService legacyKeywordMigrationService) {
        super(
                keywordMigrationRepository,
                legacyKeywordRepository,
                legacyKeywordMigrationService
        );
    }

    @Override
    protected KeywordPageMigration firstPageMigration(
            Long userId,
            boolean isSuccess,
            Page<LegacyKeywordEntity> legacyPage
    ) {
        return KeywordPageMigration.first(
                isSuccess,
                userId,
                PAGE_SIZE,
                legacyPage.getTotalElements()
        );
    }
}