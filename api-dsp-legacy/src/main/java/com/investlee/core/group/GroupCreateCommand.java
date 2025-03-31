package com.investlee.core.group;

public record GroupCreateCommand(
        String name,
        Long campaignId,
        Long userId,
        String linkUrl) {
}
