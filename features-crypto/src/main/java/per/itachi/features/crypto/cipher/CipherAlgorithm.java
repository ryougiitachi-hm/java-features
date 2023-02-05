package per.itachi.features.crypto.cipher;

import lombok.Getter;

/**
 * DES - Data Encryption Standard, 56-bit secret key. Symmetric
 * IDEA - International Data Encryption Algorithm, 128-bit secret key.
 * AES - Advanced Encryption Standard. Rijndael.
 * */
@Getter
public enum CipherAlgorithm {

    BLOWFISH("BlowFish"),
    DES("DES"),
    RC2("RC2"),
    RC4("RC4"),
    IDEA("IDEA"),
    AES("AES"),
    ;

    private final String algorithmName;

    CipherAlgorithm(String algorithmName) {
        this.algorithmName = algorithmName;
    }
}
