package com.investlee.core.campaign;

import com.investlee.domain.campaign.LegacyCampaignEntity;
import com.investlee.domain.campaign.LegacyCampaignRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final LegacyCampaignRepository legacyCampaignRepository;

    @Transactional
    public CampaignResult create(CampaignCreateCommand command) {
        LegacyCampaignEntity newLegacyCampaignEntity = LegacyCampaignEntity.of(command.name(), command.userId(),
                command.budget());
        return CampaignResult.from(legacyCampaignRepository.save(newLegacyCampaignEntity));
    }

    public CampaignResult find(Long id) {
        return CampaignResult.from(findById(id));

    }

    private LegacyCampaignEntity findById(Long id) {
        return legacyCampaignRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public CampaignResult updateName(Long id, String name) {
        LegacyCampaignEntity legacyCampaignEntity = findById(id);
        legacyCampaignEntity.updateName(name);
        return CampaignResult.from(legacyCampaignRepository.save(legacyCampaignEntity));
    }

    @Transactional
    public CampaignResult updateBudget(Long id, Long budget) {
        LegacyCampaignEntity legacyCampaignEntity = findById(id);
        legacyCampaignEntity.updateBudget(budget);
        return CampaignResult.from(legacyCampaignRepository.save(legacyCampaignEntity));
    }

    @Transactional
    public CampaignResult delete(Long id) {
        LegacyCampaignEntity legacyCampaignEntity = findById(id);
        legacyCampaignEntity.delete();
        return CampaignResult.from(legacyCampaignRepository.save(legacyCampaignEntity));
    }
}
