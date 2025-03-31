package com.investlee.core.group;

import com.investlee.domain.group.GroupEntity;
import com.investlee.domain.group.GroupRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    @Transactional
    public GroupResult create(GroupCreateCommand command) {
        GroupEntity newGroupEntity = GroupEntity.of(
                command.name(),
                command.campaignId(),
                command.userId(),
                command.linkUrl());
        return GroupResult.from(groupRepository.save(newGroupEntity));
    }

    public GroupResult find(Long id) {
        return GroupResult.from(findById(id));
    }

    private GroupEntity findById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public GroupResult updateName(Long id, String name) {
        GroupEntity groupEntity = findById(id);
        groupEntity.updateName(name);
        return GroupResult.from(groupRepository.save(groupEntity));
    }

    @Transactional
    public GroupResult updateLinkUrl(Long id, String linkUrl) {
        GroupEntity groupEntity = findById(id);
        groupEntity.updateLinkUrl(linkUrl);
        return GroupResult.from(groupRepository.save(groupEntity));
    }

    @Transactional
    public GroupResult delete(Long id) {
        GroupEntity groupEntity = findById(id);
        groupEntity.delete();
        return GroupResult.from(groupRepository.save(groupEntity));
    }
}
