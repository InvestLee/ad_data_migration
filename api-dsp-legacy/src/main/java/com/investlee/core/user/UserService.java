package com.investlee.core.user;

import com.investlee.domain.user.LegacyUserEntity;
import com.investlee.domain.user.LegacyUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final LegacyUserRepository userRepository;

    @Transactional
    public UserResult create(
            UserCreateCommand command) {
        LegacyUserEntity newLegacyUserEntity = LegacyUserEntity.of(command.name());
        return UserResult.from(userRepository.save(newLegacyUserEntity));
    }

    public UserResult find(Long id) {
        return UserResult.from(findById(id));
    }

    private LegacyUserEntity findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public UserResult updateName(Long id, String name) {
        LegacyUserEntity legacyUserEntity = findById(id);
        legacyUserEntity.updateName(name);
        return UserResult.from(userRepository.save(legacyUserEntity));
    }

    @Transactional
    public UserResult delete(Long id) {
        LegacyUserEntity legacyUserEntity = findById(id);
        legacyUserEntity.delete();
        return UserResult.from(userRepository.save(legacyUserEntity));
    }
}
