package per.itachi.features.crypto.digest;

import lombok.Getter;

/**
 * JDK supports MD2, MD5, SHA-1, SHA-224, SHA-256, SHA-384, SHA-512。
 * MD5 - 128 bits
 * SHA-1 - 160 bits (192, 256)
 * */
@Getter
public enum DigestAlgorithm {

    MD2("MD2"),
    MD5("MD5"),
    SHA1("SHA1"), // also fine for SHA-1
    SHA224("SHA224"),
    SHA256("SHA256"), // also fine for SHA-256
    SHA384("SHA384"), // also fine for SHA-384
    SHA512("SHA512") // also fine for SHA-512
    ;

    /**
     * The digest algorithm name supported by framework.
     * */
    private final String algorithmName;

    DigestAlgorithm(String algorithmName) {
        this.algorithmName = algorithmName;
    }
}
