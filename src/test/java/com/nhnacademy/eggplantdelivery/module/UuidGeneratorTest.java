package com.nhnacademy.eggplantdelivery.module;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UuidGeneratorTest {

    private static final String NAMESPACE_URL = "6ba7b811-9dad-11d1-80b4-00c04fd430c8";

    @Test
    @DisplayName("ver5 UUID 추가")
    void generateType5UUID() {
        UUID uuid = UuidGenerator.generateType5Uuid(NAMESPACE_URL, "baeldung.com");

        assertEquals("aeff44a5-8a61-52b6-bcbe-c8e5bd7d0300", uuid.toString());
        assertEquals(5, uuid.version());
        assertEquals(2, uuid.variant());
    }
}
