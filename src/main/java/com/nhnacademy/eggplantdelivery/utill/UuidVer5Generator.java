package com.nhnacademy.eggplantdelivery.utill;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * UUID ver 5를 만들어 내는 클래스 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
public class UuidVer5Generator {

    private UuidVer5Generator() {
    }

    private static final String BASIC_UUID_STRING = "38400000-8cf0-11bd-b23e-10b96e4ef00d";

    /**
     * UUID ver 5를 만드는 로직을 처리하는 메서드입니다.
     *
     * @param name 각 서버별로 받은 배송번호와 Host 입니다.
     * @return ver 5 의 UUID 입니다.
     * @author 김훈민, 조재철
     */
    public static UUID ver5UuidFromNamespaceAndBytes(byte[] name) {
        Objects.requireNonNull(UUID.fromString(BASIC_UUID_STRING), "namespace is null");
        Objects.requireNonNull(name, "name is null");

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException nsae) {
            throw new InternalError("SHA-256 not supported");
        }

        md.update(toBytes(UUID.fromString(BASIC_UUID_STRING)));
        md.update(name);
        byte[] sha256Bytes = md.digest();

        sha256Bytes[6] &= 0x0f; /* clear version */
        sha256Bytes[6] |= 0x50; /* set to version 5 */
        sha256Bytes[8] &= 0x3f; /* clear variant */
        sha256Bytes[8] |= 0x80; /* set to IETF variant */

        return fromBytes(Arrays.copyOfRange(sha256Bytes, 0, 16));
    }

    private static UUID fromBytes(byte[] data) {
        long msb = 0;
        long lsb = 0;

        assert data.length == 16 : "data must be 16 bytes in length";

        for (int i = 0; i < 8; i++) {
            msb = (msb << 8) | (data[i] & 0xff);
        }

        for (int i = 8; i < 16; i++) {
            lsb = (lsb << 8) | (data[i] & 0xff);
        }

        return new UUID(msb, lsb);
    }

    private static byte[] toBytes(UUID uuid) {
        byte[] out = new byte[16];

        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();

        for (int i = 0; i < 8; i++) {
            out[i] = (byte) ((msb >> ((7 - i) * 8)) & 0xff);
        }

        for (int i = 8; i < 16; i++) {
            out[i] = (byte) ((lsb >> ((15 - i) * 8)) & 0xff);
        }

        return out;
    }

}
