package com.investlee.domain.migration.group;

import com.investlee.domain.AggregateType;
import com.investlee.domain.migration.PageMigration;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class GroupPageMigration extends PageMigration<GroupPageMigration> {

    private GroupPageMigration(
            Long id,
            Integer pageNumber,
            Integer pageSize,
            Long totalCount) {
        super(
                id,
                pageNumber,
                pageSize,
                totalCount
        );
    }

    public static GroupPageMigration first(
            boolean isSuccess,
            Long id,
            Integer pageSize,
            Long totalCount
    ) {
        if (isSuccess) {
            return new GroupPageMigration(
                    id,
                    INIT_PAGE_NUMBER,
                    pageSize,
                    totalCount
            );
        }
        return new GroupPageMigration(
                id,
                NOT_STARTED_PAGE_NUMBER,
                pageSize,
                totalCount
        );
    }

    @Override
    protected AggregateType aggregateType() {
        return AggregateType.GROUP;
    }

}