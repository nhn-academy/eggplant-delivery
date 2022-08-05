package com.nhnacademy.eggplantdelivery.module;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nhnacademy.eggplantdelivery.utill.UuidVer5Generator;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UuidVer5GeneratorTest {

    @Test
    @DisplayName("ver5 Uuid 를 만들어 내는 코드 입니다.")
    void nameUuidFromNamespaceAndBytes() {
        UUID uuid = UuidVer5Generator.ver5UuidFromNamespaceAndBytes(("Host" + "OrderNo").getBytes(
            StandardCharsets.UTF_8));

        assertEquals("99d93b5e-703d-54fa-8e9a-7c84c344644e", uuid.toString());
        assertEquals(5, uuid.version());
        assertEquals(2, uuid.variant());
    }
}