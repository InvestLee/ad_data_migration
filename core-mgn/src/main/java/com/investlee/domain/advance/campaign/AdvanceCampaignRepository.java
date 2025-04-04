package com.investlee.domain.advance.campaign;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvanceCampaignRepository extends CrudRepository<AdvanceCampaignEntity, Long> {
}
