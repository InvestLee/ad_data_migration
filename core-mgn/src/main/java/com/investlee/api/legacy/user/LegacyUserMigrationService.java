package com.investlee.api.legacy.user;

import com.investlee.api.legacy.LegacyMigrationService;
import com.investlee.domain.advance.user.AdvanceUserEntity;
import com.investlee.domain.advance.user.AdvanceUserRepository;
import com.investlee.domain.legacy.user.LegacyUserEntity;
import com.investlee.domain.legacy.user.LegacyUserRepository;
import org.springframework.stereotype.Service;

@Service
public class LegacyUserMigrationService extends LegacyMigrationService<
        LegacyUserEntity,
        AdvanceUserEntity
        > {

    public LegacyUserMigrationService(
            LegacyUserRepository legacyUserRepository,
            AdvanceUserRepository advanceUserRepository,
            LegacyUserConverter legacyUserConverter) {
        super(
                legacyUserRepository,
                advanceUserRepository,
                legacyUserConverter
        );
    }
}