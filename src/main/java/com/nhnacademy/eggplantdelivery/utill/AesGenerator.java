package com.nhnacademy.eggplantdelivery.utill;

import com.nhnacademy.eggplantdelivery.exception.DecodeFailureException;
import com.nhnacademy.eggplantdelivery.exception.EncodeFailureException;
import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 개인정보를 Aes 알고리즘 방식을 사용해 암호화 하는 유틸리티 클래스 입니다.
 *
 * @author 김훈민, 조재철
 * @version  1.0.0
 */
@Component
public class AesGenerator {
    private final SecretKeySpec secretKeySpec;
    private final String transformation;

    /**
     * Aes 객체를 생성하기 위한 생성자입니다.
     *
     * @param userInformationProtectionValue salt 를 위한 일반 텍스트 문자열입니다.
     */
    public AesGenerator(String userInformationProtectionValue,
                        @Value("${aes.algorithm}") String transformation) {
        this.secretKeySpec =
            new SecretKeySpec(userInformationProtectionValue.getBytes(StandardCharsets.UTF_8),
                "AES");
        this.transformation = transformation;
    }


    /**
     * Aes-256 알고리즘으로 평문을 암호화하는 메서드입니다.
     *
     * @param plainText 평문입니다.
     * @return 암호화된 문자열입니다.
     * @throws EncodeFailureException EncodeFailureException
     */
    public String aesEcbEncode(String plainText) {
        try {
            Cipher c = Cipher.getInstance(transformation);
            c.init(Cipher.ENCRYPT_MODE, this.secretKeySpec);

            byte[] encrpytionByte = c.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

            return Base64.encodeBase64String(encrpytionByte);
        } catch (Exception e) {
            throw new EncodeFailureException();
        }
    }

    /**
     * Aes-256 알고리즘으로 암호화된 문자열을 평문을 복호화하는 메서드입니다.
     *
     * @param encodedText 암호화된 문자열입니다.
     * @return 복호화 된 평문입니다.
     * @throws DecodeFailureException DecodeFailureException
     */
    public String aesEcbDecode(String encodedText) {
        try {
            Cipher c = Cipher.getInstance(transformation);
            c.init(Cipher.DECRYPT_MODE, this.secretKeySpec);

            byte[] decodedByte = Base64.decodeBase64(encodedText);

            return new String(c.doFinal(decodedByte), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new DecodeFailureException();
        }
    }

}
