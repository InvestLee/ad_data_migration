package com.investlee.core.keyword;

import com.investlee.domain.keyword.KeywordEntity;
import com.investlee.domain.keyword.KeywordRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KeywordService {

    private final KeywordRepository keywordRepository;

    @Transactional
    public KeywordResult create(
            KeywordCreateCommand command) {
        KeywordEntity newKeywordEntity = KeywordEntity.of(
                command.text(),
                command.groupId(),
                command.userId());
        return KeywordResult.from(keywordRepository.save(newKeywordEntity));
    }

    public KeywordResult find(Long id) {
        return KeywordResult.from(findById(id));
    }

    private KeywordEntity findById(Long id) {
        return keywordRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public KeywordResult delete(Long id) {
        KeywordEntity keywordEntity = findById(id);
        keywordEntity.delete();
        return KeywordResult.from(keywordRepository.save(keywordEntity));
    }
}
