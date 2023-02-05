package per.itachi.features.crypto.cipher;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import per.itachi.features.crypto.common.CommonBusinessException;

/**
 * thread-safe
 *
 * Please refer to https://blog.csdn.net/weixin_42504805/article/details/124709352 for each algorithm.
 * */
public class CipherStrategySelector {

    public byte[] generateSecretKey(CipherAlgorithm algorithm) {
        try {
            // TODO: There are still a lot of function to look into SecureRandom and KeyGenerator.
            SecureRandom secureRandom = new SecureRandom();
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm.getAlgorithmName());
            keyGenerator.init(secureRandom);;
            SecretKey key =keyGenerator.generateKey();
            return key.getEncoded();
        }
        catch (NoSuchAlgorithmException e) {
            throw new CommonBusinessException(e);
        }
    }

    /**
     * @param cipherKeyBase64 The cipher key is required to be base64.
     * */
    public void encrypt(CipherAlgorithm algorithm, String cipherKeyBase64, byte[] originalData) {
        byte[] bytesCipherKey = Base64.getDecoder().decode(cipherKeyBase64);
        encrypt(algorithm, bytesCipherKey, originalData);
    }

    /**
     * @param cipherKey The pure string, not base64.
     * */
    public void encrypt(CipherAlgorithm algorithm, String cipherKey, Charset cipherKeyCharset, byte[] originalData) {
        byte[] bytesCipherKey = cipherKey.getBytes(cipherKeyCharset);
        encrypt(algorithm, bytesCipherKey, originalData);
    }

    public void encrypt(CipherAlgorithm algorithm, byte[] cipherKey, byte[] originalData) {
        try {
            SecretKey key = new SecretKeySpec(cipherKey, algorithm.getAlgorithmName());
            Cipher cipher = Cipher.getInstance(algorithm.getAlgorithmName());
            cipher.init(Cipher.ENCRYPT_MODE, key);
            cipher.update(originalData);
//            cipher.updateAAD(originalData);
            cipher.doFinal();
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | IllegalBlockSizeException | BadPaddingException e) {
            throw new CommonBusinessException(e);
        }
    }

    /**
     * @param cipherKey The pure string, not base64.
     * */
    public void decrypt(CipherAlgorithm algorithm, String cipherKey, Charset cipherKeyCharset, byte[] encryptedData) {
        byte[] bytesCipherKey = cipherKey.getBytes(cipherKeyCharset);
        decrypt(algorithm, bytesCipherKey, encryptedData);
    }

    public void decrypt(CipherAlgorithm algorithm, byte[] cipherKey, byte[] encryptedData) {
        try {
            Cipher cipher = Cipher.getInstance(algorithm.getAlgorithmName());
//            cipher.init(Cipher.DECRYPT_MODE, key);
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new CommonBusinessException(e);
        }
    }
}
