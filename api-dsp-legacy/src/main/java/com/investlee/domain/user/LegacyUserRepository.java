package com.investlee.domain.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegacyUserRepository extends CrudRepository<LegacyUserEntity, Long> {
}
