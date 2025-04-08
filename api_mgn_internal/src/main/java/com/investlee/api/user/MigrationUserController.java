package com.investlee.api.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/migration/v1/user")
public class MigrationUserController {

    private final MigrationUserService migrationUserService;

    @PostMapping("/{userId}/agree")
    public MigrationUserResponse agreeMigration(@PathVariable Long userId) {
        MigrationUserResult result = migrationUserService.agree(userId);
        return new MigrationUserResponse(
                result.id(),
                result.status(),
                result.agreedDate(),
                result.updateDate()
        );
    }

    @GetMapping("/{userId}")
    public MigrationUserResponse findMigrationUser(@PathVariable Long userId) {
        MigrationUserResult result = migrationUserService.findById(userId);
        return new MigrationUserResponse(
                result.id(),
                result.status(),
                result.agreedDate(),
                result.updateDate()
        );
    }

    @PutMapping("/{userId}/retry")
    public MigrationUserResponse retryMigrationByUser(@PathVariable Long userId) {
        MigrationUserResult result = migrationUserService.retry(userId);
        return new MigrationUserResponse(
                result.id(),
                result.status(),
                result.agreedDate(),
                result.updateDate()
        );
    }
}
