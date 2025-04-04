package com.investlee.core.group;

import com.investlee.domain.group.LegacyGroupEntity;
import com.investlee.domain.group.LegacyGroupRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final LegacyGroupRepository legacyGroupRepository;

    @Transactional
    public GroupResult create(GroupCreateCommand command) {
        LegacyGroupEntity newLegacyGroupEntity = LegacyGroupEntity.of(
                command.name(),
                command.campaignId(),
                command.userId(),
                command.linkUrl());
        return GroupResult.from(legacyGroupRepository.save(newLegacyGroupEntity));
    }

    public GroupResult find(Long id) {
        return GroupResult.from(findById(id));
    }

    private LegacyGroupEntity findById(Long id) {
        return legacyGroupRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public GroupResult updateName(Long id, String name) {
        LegacyGroupEntity legacyGroupEntity = findById(id);
        legacyGroupEntity.updateName(name);
        return GroupResult.from(legacyGroupRepository.save(legacyGroupEntity));
    }

    @Transactional
    public GroupResult updateLinkUrl(Long id, String linkUrl) {
        LegacyGroupEntity legacyGroupEntity = findById(id);
        legacyGroupEntity.updateLinkUrl(linkUrl);
        return GroupResult.from(legacyGroupRepository.save(legacyGroupEntity));
    }

    @Transactional
    public GroupResult delete(Long id) {
        LegacyGroupEntity legacyGroupEntity = findById(id);
        legacyGroupEntity.delete();
        return GroupResult.from(legacyGroupRepository.save(legacyGroupEntity));
    }
}
