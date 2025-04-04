package com.investlee.api.legacy;

import com.investlee.domain.advance.AdvanceBaseEntity;
import com.investlee.domain.legacy.LegacyBaseEntity;
import com.investlee.domain.legacy.LegacyPageableRepository;
import com.investlee.domain.migration.PageMigration;
import com.investlee.domain.migration.PageMigrationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
public abstract class PageLegacyMigrationService<
        P extends PageMigration<P>,
        Legacy extends LegacyBaseEntity,
        Advance extends AdvanceBaseEntity> {

    public final static Integer PAGE_SIZE = 1000;
    protected final PageMigrationRepository<P> pageMigrationRepository;
    protected final LegacyPageableRepository<Legacy> legacyPageableRepository;
    protected final LegacyMigrationService<Legacy, Advance> legacyMigrationService;

    @Transactional
    public PageMigrationResult migrate(Long userId) {
        return pageMigrationRepository.findById(userId)
                .map(this::migrateNextPage)
                .orElseGet(() -> startPageMigration(userId));
    }

    private PageMigrationResult startPageMigration(Long userId) {
        Integer pageNumber = PageMigration.INIT_PAGE_NUMBER;
        Page<Legacy> legacyPage = findPage(
                userId,
                pageNumber
        );
        boolean isSuccess = migrate(legacyPage);
        P pageMigration = firstPageMigration(
                userId,
                isSuccess,
                legacyPage
        );
        pageMigrationRepository.save(pageMigration);
        return new PageMigrationResult(
                userId,
                pageMigration.getPageNumber(),
                legacyPage.getTotalPages(),
                pageMigration.getTotalCount(),
                isSuccess
        );
    }

    protected abstract P firstPageMigration(
            Long userId,
            boolean isSuccess,
            Page<Legacy> legacyPage
    );

    protected Page<Legacy> findPage(Long userId, Integer pageNumber) {
        return legacyPageableRepository.findAllByUserIdAndDeletedAtIsNullOrderById(
                userId,
                PageRequest.of(pageNumber, PAGE_SIZE)
        );
    }

    private boolean migrate(Page<Legacy> legacyPage) {
        return legacyMigrationService.migrate(legacyPage.toList());
    }

    private PageMigrationResult migrateNextPage(P pageMigration) {
        Integer pageNumber = pageMigration.nextPageNumber();
        Page<Legacy> legacyPage = findPage(
                pageMigration.getId(),
                pageNumber
        );
        boolean isSuccess = migrate(legacyPage);
        pageMigration.progress(
                isSuccess,
                legacyPage.getTotalElements()
        );
        pageMigrationRepository.save(pageMigration);
        return new PageMigrationResult(
                pageMigration.getId(),
                pageMigration.getPageNumber(),
                legacyPage.getTotalPages(),
                pageMigration.getTotalCount(),
                isSuccess
        );
    }
}