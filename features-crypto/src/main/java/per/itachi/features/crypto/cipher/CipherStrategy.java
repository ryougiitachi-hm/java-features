package per.itachi.features.crypto.cipher;

import java.util.List;

public interface CipherStrategy {

    List<CipherAlgorithm> supportsAlgorithms();

    void encrypt();

    void decrypt();
}
