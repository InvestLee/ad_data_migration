package com.investlee.core.keyword;

public record KeywordCreateCommand(
        String text,
        Long groupId,
        Long userId) {
}
