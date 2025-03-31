package com.investlee.api.keyword;

public record KeywordCreateRequest(
        String text,
        Long groupId) {
}
