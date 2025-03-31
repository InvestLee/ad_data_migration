package com.investlee.api.campaign;

import com.investlee.core.campaign.CampaignCreateCommand;
import com.investlee.core.campaign.CampaignService;
import com.investlee.core.user.UserResult;
import com.investlee.core.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/campaign/v1")
public class CampaignController {

    private final CampaignService campaignService;
    private final UserService userService;

    @PostMapping("")
    public CampaignResponse create(@RequestBody CampaignCreateRequest request) {
        UserResult userResult = userService.find(request.userId());
        return CampaignResponse.from(
                campaignService.create(
                        new CampaignCreateCommand(
                                request.name(),
                                userResult.id(),
                                request.budget()
                        )
                )
        );
    }

    @GetMapping("/{id}")
    public CampaignResponse find(@PathVariable("id") Long id) {
        return CampaignResponse.from(campaignService.find(id));
    }

    @PatchMapping("/name")
    public CampaignResponse updateName(@RequestBody CampaignUpdateNameRequest request) {
        return CampaignResponse.from(
                campaignService.updateName(
                        request.id(),
                        request.name()
                )
        );
    }

    @PatchMapping("/budget")
    public CampaignResponse updateBudget(@RequestBody CampaignUpdateBudgetRequest request) {
        return CampaignResponse.from(
                campaignService.updateBudget(
                        request.id(),
                        request.budget()
                )
        );
    }

    @DeleteMapping("/{id}")
    public CampaignResponse delete(@PathVariable("id") Long id) {
        return CampaignResponse.from(campaignService.delete(id));
    }
}
