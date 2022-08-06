package com.nhnacademy.eggplantdelivery.utill;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class UuidVer5GeneratorTest {

    @Test
    @DisplayName("ver5 Uuid 를 만들어 내는 코드 입니다.")
    void testNameUuidFromNamespaceAndBytes() {
        UUID uuid = UuidVer5Generator.ver5UuidFromNamespaceAndBytes(("Host" + "OrderNo").getBytes(
            StandardCharsets.UTF_8));

        assertEquals("99d93b5e-703d-54fa-8e9a-7c84c344644e", uuid.toString());
        assertEquals(5, uuid.version());
        assertEquals(2, uuid.variant());
    }

    @Test
    @DisplayName("지원하지 않는 암호화 방식 사용시 에러 처리")
    void testNameUuidFromNamespaceAndBytesThrownByNoSuchAlgorithmException() throws NoSuchAlgorithmException {
        try (MockedStatic<MessageDigest> mockedStatic = mockStatic(MessageDigest.class)) {
            mockedStatic.when((MockedStatic.Verification) MessageDigest.getInstance(anyString()))
                        .thenThrow(new NoSuchAlgorithmException());
            byte[] bytes = ("Host" + "OrderNo").getBytes(StandardCharsets.UTF_8);
            assertThatThrownBy(() ->
                UuidVer5Generator.ver5UuidFromNamespaceAndBytes(bytes)
            ).isInstanceOf(InternalError.class)
             .hasMessageContaining("SHA-256 not supported");
        }

    }

}