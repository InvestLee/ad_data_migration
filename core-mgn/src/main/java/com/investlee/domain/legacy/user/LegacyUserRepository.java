package com.investlee.domain.legacy.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegacyUserRepository extends CrudRepository<LegacyUserEntity, Long> {
}
