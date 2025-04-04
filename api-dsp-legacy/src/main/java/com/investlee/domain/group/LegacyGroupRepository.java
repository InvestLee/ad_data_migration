package com.investlee.domain.group;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegacyGroupRepository extends CrudRepository<LegacyGroupEntity, Long> {
}
