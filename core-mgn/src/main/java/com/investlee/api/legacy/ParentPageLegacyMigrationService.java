package com.investlee.api.legacy;

import com.investlee.domain.advance.AdvanceBaseEntity;
import com.investlee.domain.legacy.LegacyBaseEntity;
import com.investlee.domain.legacy.LegacyPageableRepository;
import com.investlee.domain.migration.PageMigration;
import com.investlee.domain.migration.PageMigrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Slf4j
public abstract class ParentPageLegacyMigrationService<
        P extends PageMigration<P>,
        Legacy extends LegacyBaseEntity,
        Advance extends AdvanceBaseEntity,
        LegacyParent extends LegacyBaseEntity
        > extends PageLegacyMigrationService<
        P,
        Legacy,
        Advance
        > {

    public final static Integer PARENT_PAGE_SIZE = 10;
    protected final LegacyPageableRepository<LegacyParent> parentRepository;

    public ParentPageLegacyMigrationService(
            PageMigrationRepository<P> pageMigrationRepository,
            LegacyPageableRepository<Legacy> legacyPageableRepository,
            LegacyMigrationService<Legacy, Advance> legacyMigrationService,
            LegacyPageableRepository<LegacyParent> parentRepository) {
        super(
                pageMigrationRepository,
                legacyPageableRepository,
                legacyMigrationService
        );
        this.parentRepository = parentRepository;
    }


    @Override
    protected Page<Legacy> findPage(
            Long userId,
            Integer pageNumber) {
        Page<LegacyParent> legacyParentPage = findLegacyParentPage(
                userId,
                pageNumber
        );
        List<Long> legacyParentIds = getLegacyParentIds(legacyParentPage);
        List<Legacy> legacies = findAllByParentIdsIn(legacyParentIds);
        return new PageImpl<>(
                legacies,
                PageRequest.of(pageNumber, PARENT_PAGE_SIZE),
                legacyParentPage.getTotalElements()
        );
    }

    private Page<LegacyParent> findLegacyParentPage(
            Long userId,
            Integer pageNumber
    ) {
        return parentRepository.findAllByUserIdAndDeletedAtIsNullOrderById(
                userId,
                PageRequest.of(pageNumber, PARENT_PAGE_SIZE)
        );
    }

    private List<Long> getLegacyParentIds(Page<LegacyParent> legacyParentPage) {
        return legacyParentPage.stream()
                .map(LegacyBaseEntity::getId)
                .toList();
    }

    protected abstract List<Legacy> findAllByParentIdsIn(List<Long> legacyParentIds);

}
