package com.investlee.core.campaign;

import com.investlee.domain.campaign.CampaignEntity;
import com.investlee.domain.campaign.CampaignRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;

    @Transactional
    public CampaignResult create(CampaignCreateCommand command) {
        CampaignEntity newCampaignEntity = CampaignEntity.of(command.name(), command.userId(),
                command.budget());
        return CampaignResult.from(campaignRepository.save(newCampaignEntity));
    }

    public CampaignResult find(Long id) {
        return CampaignResult.from(findById(id));

    }

    private CampaignEntity findById(Long id) {
        return campaignRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public CampaignResult updateName(Long id, String name) {
        CampaignEntity campaignEntity = findById(id);
        campaignEntity.updateName(name);
        return CampaignResult.from(campaignRepository.save(campaignEntity));
    }

    @Transactional
    public CampaignResult updateBudget(Long id, Long budget) {
        CampaignEntity campaignEntity = findById(id);
        campaignEntity.updateBudget(budget);
        return CampaignResult.from(campaignRepository.save(campaignEntity));
    }

    @Transactional
    public CampaignResult delete(Long id) {
        CampaignEntity campaignEntity = findById(id);
        campaignEntity.delete();
        return CampaignResult.from(campaignRepository.save(campaignEntity));
    }
}
