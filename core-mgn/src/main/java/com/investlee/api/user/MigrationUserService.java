package com.investlee.api.user;

import com.investlee.api.legacy.MigrationService;
import com.investlee.api.legacy.user.LegacyUserMigrationService;
import com.investlee.domain.migration.user.MigrationUserEntity;
import com.investlee.domain.migration.user.MigrationUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MigrationUserService implements MigrationService {

    private final MigrationUserRepository migrationUserRepository;
    private final LegacyUserMigrationService legacyUserMigrationService;

    @Transactional
    public MigrationUserResult agree(Long userId) {
        migrationUserRepository.findById(userId)
                .ifPresent(migrationUserEntity -> {
                    throw new AlreadyAgreedException(String.format("User [ID: %d] already agreed", userId));
                });
        return MigrationUserResult.from(
                migrationUserRepository.save(
                        MigrationUserEntity.agreed(userId)
                )
        );
    }

    private MigrationUserEntity find(Long userId) {
        return migrationUserRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public MigrationUserResult findById(Long userId) {
        return MigrationUserResult.from(find(userId));
    }

    public boolean isDisagreed(Long migrationUserId) {
        return migrationUserRepository.findById(migrationUserId).isEmpty();
    }

    @Transactional
    public MigrationUserResult startMigration(Long userId)
            throws StartMigrationFailedException {
        boolean result = migrate(userId);
        if (result) {
            return progressMigration(userId);
        }
        throw new StartMigrationFailedException();
    }

    @Override
    public boolean migrate(Long id) {
        return legacyUserMigrationService.migrate(id);
    }

    @Transactional
    public MigrationUserResult progressMigration(Long userId) {
        MigrationUserEntity migrationUserEntity = find(userId);
        migrationUserEntity.progressMigration();
        return MigrationUserResult.from(
                migrationUserRepository.save(migrationUserEntity)
        );
    }

    @Transactional
    public MigrationUserResult retry(Long userId) {
        MigrationUserEntity migrationUserEntity = find(userId);
        migrationUserEntity.retry();
        return MigrationUserResult.from(
                migrationUserRepository.save(migrationUserEntity)
        );
    }
}