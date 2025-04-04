package com.investlee.api.legacy.keyword;

import com.investlee.api.legacy.LegacyMigrationService;
import com.investlee.domain.advance.keyword.AdvanceKeywordEntity;
import com.investlee.domain.advance.keyword.AdvanceKeywordRepository;
import com.investlee.domain.legacy.keyword.LegacyKeywordEntity;
import com.investlee.domain.legacy.keyword.LegacyKeywordRepository;
import org.springframework.stereotype.Service;

@Service
public class LegacyKeywordMigrationService extends
        LegacyMigrationService<
                LegacyKeywordEntity,
                AdvanceKeywordEntity
                > {

    public LegacyKeywordMigrationService(LegacyKeywordRepository legacyKeywordRepository,
                                         AdvanceKeywordRepository recentKeywordRepository,
                                         LegacyKeywordConverter legacyKeywordConverter) {
        super(
                legacyKeywordRepository,
                recentKeywordRepository,
                legacyKeywordConverter
        );
    }
}