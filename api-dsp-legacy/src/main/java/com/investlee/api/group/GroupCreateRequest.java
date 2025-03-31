package com.investlee.api.group;

public record GroupCreateRequest(
        String name,
        Long campaignId,
        String linkUrl
) {
}
