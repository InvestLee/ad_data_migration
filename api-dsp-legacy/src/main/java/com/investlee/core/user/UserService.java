package com.investlee.core.user;

import com.investlee.domain.user.UserEntity;
import com.investlee.domain.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResult create(
            UserCreateCommand command) {
        UserEntity newUserEntity = UserEntity.of(command.name());
        return UserResult.from(userRepository.save(newUserEntity));
    }

    public UserResult find(Long id) {
        return UserResult.from(findById(id));
    }

    private UserEntity findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public UserResult updateName(Long id, String name) {
        UserEntity userEntity = findById(id);
        userEntity.updateName(name);
        return UserResult.from(userRepository.save(userEntity));
    }

    @Transactional
    public UserResult delete(Long id) {
        UserEntity userEntity = findById(id);
        userEntity.delete();
        return UserResult.from(userRepository.save(userEntity));
    }
}
