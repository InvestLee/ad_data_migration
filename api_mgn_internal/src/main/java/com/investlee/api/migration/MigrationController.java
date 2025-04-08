package com.investlee.api.migration;

import com.investlee.api.dispatcher.MigrationDispatcher;
import com.investlee.api.monitoring.MigrationMonitor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/migration/v1/")
public class MigrationController {

    private final MigrationDispatcher migrationDispatcher;
    private final MigrationMonitor migrationMonitor;

    @PutMapping("/retry")
    public MigrationRetryResponse retryMigration(@RequestBody MigrationRetryRequest request) {
        boolean result = migrationDispatcher
                .dispatch(request.userId(),
                        request.aggregateId(),
                        request.aggregateType()
                );
        return new MigrationRetryResponse(result);
    }

    @GetMapping("/progress")
    public MigrationProgressResponse getMigrationProgress() {
        return MigrationProgressResponse.from(migrationMonitor.measure());
    }
}