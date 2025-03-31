package com.investlee.api.group;

import com.investlee.core.campaign.CampaignResult;
import com.investlee.core.campaign.CampaignService;
import com.investlee.core.group.GroupCreateCommand;
import com.investlee.core.group.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/group/v1")
public class GroupController {

    private final GroupService groupService;
    private final CampaignService campaignService;

    @PostMapping("")
    public GroupResponse create(@RequestBody GroupCreateRequest request) {
        CampaignResult campaign = campaignService.find(request.campaignId());
        return GroupResponse.from(groupService.create(
                new GroupCreateCommand(
                        request.name(),
                        campaign.id(),
                        campaign.userId(),
                        request.linkUrl())));
    }

    @GetMapping("/{id}")
    public GroupResponse find(@PathVariable("id") Long id) {
        return GroupResponse.from(groupService.find(id));
    }

    @PatchMapping("/name")
    public GroupResponse updateName(@RequestBody GroupUpdateNameRequest request) {
        return GroupResponse.from(
                groupService.updateName(
                        request.id(),
                        request.name()
                )
        );
    }

    @PatchMapping("/linkUrl")
    public GroupResponse updateLinkUrl(@RequestBody GroupUpdateLinkUrlRequest request) {
        return GroupResponse.from(
                groupService.updateLinkUrl(
                        request.id(),
                        request.linkUrl()
                )
        );
    }

    @DeleteMapping("/{id}")
    public GroupResponse delete(@PathVariable("id") Long id) {
        return GroupResponse.from(groupService.delete(id));
    }
}
