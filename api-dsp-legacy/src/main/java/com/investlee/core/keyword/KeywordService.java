package com.investlee.core.keyword;

import com.investlee.domain.keyword.LegacyKeywordEntity;
import com.investlee.domain.keyword.LegacyKeywordRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KeywordService {

    private final LegacyKeywordRepository legacyKeywordRepository;

    @Transactional
    public KeywordResult create(
            KeywordCreateCommand command) {
        LegacyKeywordEntity newLegacyKeywordEntity = LegacyKeywordEntity.of(
                command.text(),
                command.groupId(),
                command.userId());
        return KeywordResult.from(legacyKeywordRepository.save(newLegacyKeywordEntity));
    }

    public KeywordResult find(Long id) {
        return KeywordResult.from(findById(id));
    }

    private LegacyKeywordEntity findById(Long id) {
        return legacyKeywordRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public KeywordResult delete(Long id) {
        LegacyKeywordEntity legacyKeywordEntity = findById(id);
        legacyKeywordEntity.delete();
        return KeywordResult.from(legacyKeywordRepository.save(legacyKeywordEntity));
    }
}
