package com.investlee.api.legacy.keyword;

import com.investlee.api.legacy.LegacyConverter;
import com.investlee.domain.advance.keyword.AdvanceKeywordEntity;
import com.investlee.domain.legacy.keyword.LegacyKeywordEntity;
import org.springframework.stereotype.Component;

@Component
public class LegacyKeywordConverter implements LegacyConverter<
        LegacyKeywordEntity,
        AdvanceKeywordEntity
        > {

    public AdvanceKeywordEntity convert(LegacyKeywordEntity legacyKeywordEntity) {
        return AdvanceKeywordEntity.migrated(
                legacyKeywordEntity.getId(),
                legacyKeywordEntity.getText(),
                legacyKeywordEntity.getGroupId(),
                legacyKeywordEntity.getUserId(),
                legacyKeywordEntity.getCreatedAt(),
                legacyKeywordEntity.getDeletedAt());
    }
}