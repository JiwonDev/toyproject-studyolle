package com.jiwondev.common.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("Jasypt 암호화 확인용 테스트")
class JasyptConfigTest {

    @Test
    void jasypt_암호화_확인용_테스트() {
        // Arrange
        var url = "https://gmail.com";
        var name = "sylvester7412@gmail.com";
        var password = "kdboentjptgsoeta";
        var encryptor = createJasyptEncryptor("jiwondev");

        // Act
        var encryptedUrl = encryptor.encrypt(url);
        var encryptedName = encryptor.encrypt(name);
        var encryptedPassword = encryptor.encrypt(password);
        var decryptedUrl = encryptor.decrypt(encryptedUrl);
        var decryptedName = encryptor.decrypt(encryptedName);
        var decryptedPassword = encryptor.decrypt(encryptedPassword);

        // Assert
        final var descriptions = new StringBuilder(String.format("Assertions:%n"));
        Assertions.setDescriptionConsumer(msg -> descriptions.append(String.format("- %s%n", msg)));

        assertThat(decryptedUrl).as("url     : %-25s ENC(%s)", url, encryptedUrl)
            .isEqualTo(url);
        assertThat(decryptedName).as("name    : %-25s ENC(%s)", name, encryptedName)
            .isEqualTo(name);
        assertThat(decryptedPassword).as("password: %-25s ENC(%s)", password, encryptedPassword)
            .isEqualTo(password);

        System.out.println(descriptions);
    }

    private StringEncryptor createJasyptEncryptor(String secretKey) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(secretKey); // 암호화할 때 사용하는 키
        config.setAlgorithm("PBEWithMD5AndDES"); // 암호화 알고리즘
        config.setKeyObtentionIterations("1000"); // 반복할 해싱 회수
        config.setPoolSize("1"); // 인스턴스 pool
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); // salt 생성 클래스
        config.setStringOutputType("base64"); //인코딩 방식
        encryptor.setConfig(config);

        return encryptor;
    }

}
