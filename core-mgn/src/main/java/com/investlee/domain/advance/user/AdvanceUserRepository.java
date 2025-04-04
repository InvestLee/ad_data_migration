package com.investlee.domain.advance.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvanceUserRepository extends CrudRepository<AdvanceUserEntity, Long> {
}
