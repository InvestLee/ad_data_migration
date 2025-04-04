package com.investlee.domain.migration.keyword;

import com.investlee.domain.migration.PageMigrationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordPageMigrationRepository extends
        PageMigrationRepository<KeywordPageMigration> {

}