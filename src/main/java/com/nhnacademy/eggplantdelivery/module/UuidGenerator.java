package com.nhnacademy.eggplantdelivery.module;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;

/**
 * UUID ver 5를 만들어 내는 클래스 입니다.
 *
 * @author 김훈민, 조재철
 * @version 1.0.0
 */
public final class UuidGenerator {

    private UuidGenerator() {
    }

    /**
     * UUID ver 5를 만드는 로직을 처리하는 메서드입니다.
     *
     * @param namespace 서비스 레이어에서 랜덤32자로 생성된 파라미터 입니다.
     * @param name      각 서버별로 받은 배송번호 입니다.
     * @return ver 5 의 UUID 입니다.
     * @author 김훈민, 조재철
     */
    public static UUID generateType5Uuid(final String namespace, final String name) {

        final byte[] nameSpaceBytes = bytesFromUuid(namespace);
        final byte[] nameBytes = name.getBytes(StandardCharsets.UTF_8);
        final byte[] result = joinBytes(nameSpaceBytes, nameBytes);

        return type5UuidFromBytes(result);
    }

    /**
     * 바이트 배열에서 ver 5로 변환되는 메서드입니다.
     *
     * @param name joinBytes() 의 return 값입니다.
     * @return 최종으로 암호화된 UUID 입니다.
     * @author 김훈민, 조재철
     */
    public static UUID type5UuidFromBytes(final byte[] name) {
        final MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException exception) {
            throw new InternalError("SHA-1 not supported", exception);
        }
        final byte[] bytes = Arrays.copyOfRange(md.digest(name), 0, 16);
        bytes[6] &= 0x0f; /* clear version        */
        bytes[6] |= 0x50; /* set to version 5     */
        bytes[8] &= 0x3f; /* clear variant        */
        bytes[8] |= 0x80; /* set to IETF variant  */
        return constructType5Uuid(bytes);
    }

    private static UUID constructType5Uuid(final byte[] data) {
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

    private static byte[] bytesFromUuid(final String uuidHexString) {
        final String normalizedUuidHexString = uuidHexString.replace("-", "");

        assert normalizedUuidHexString.length() == 32;

        final byte[] bytes = new byte[16];
        for (int i = 0; i < 16; i++) {
            final byte b = hexToByte(normalizedUuidHexString.substring(i * 2, i * 2 + 2));
            bytes[i] = b;
        }
        return bytes;
    }

    public static byte hexToByte(final String hexString) {
        final int firstDigit = Character.digit(hexString.charAt(0), 16);
        final int secondDigit = Character.digit(hexString.charAt(1), 16);
        return (byte) ((firstDigit << 4) + secondDigit);
    }

    public static byte[] joinBytes(byte[] byteArray1, byte[] byteArray2) {
        final int finalLength = byteArray1.length + byteArray2.length;
        final byte[] result = new byte[finalLength];

        System.arraycopy(byteArray1, 0, result, 0, byteArray1.length);
        System.arraycopy(byteArray2, 0, result, byteArray1.length, byteArray2.length);
        return result;
    }

}

