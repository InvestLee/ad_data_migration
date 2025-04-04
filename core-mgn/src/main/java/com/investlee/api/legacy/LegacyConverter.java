package com.investlee.api.legacy;

public interface LegacyConverter<Legacy, Advance> {

    Advance convert(Legacy legacy);
}