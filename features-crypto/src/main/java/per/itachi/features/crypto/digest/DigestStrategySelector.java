package per.itachi.features.crypto.digest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import lombok.extern.slf4j.Slf4j;
import per.itachi.features.crypto.common.CommonBusinessException;

/**
 * thread-safe
 *
 * To be improved. Because data to be digested usually is very large.
 * */
@Slf4j
public class DigestStrategySelector {

    public byte[] digest(DigestAlgorithm algorithm, String originalData, Charset originalDataCharset) {
        byte[] bytesOriginalData = originalData.getBytes(originalDataCharset);
        return digest(algorithm, bytesOriginalData);
    }

    public String digestAsString(DigestAlgorithm algorithm, byte[] originalData) {
        byte[] bytesDigest = digest(algorithm, originalData);
        return Base64.getEncoder().encodeToString(bytesDigest);
    }


    public byte[] digest(DigestAlgorithm algorithm, byte[] originalData) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm.getAlgorithmName());
            messageDigest.update(originalData);
            return messageDigest.digest();
        }
        catch (NoSuchAlgorithmException e) {
            throw new CommonBusinessException(e);
        }
    }

    public String digestAsBase64(DigestAlgorithm algorithm, Path path) {
        byte[] bytesDigest = digest(algorithm, path);
        return Base64.getEncoder().encodeToString(bytesDigest);
    }

    public String digestAsHex(DigestAlgorithm algorithm, Path path) {
        byte[] bytesDigest = digest(algorithm, path);
        return convertBytesToHex(bytesDigest);
    }

    public byte[] digest(DigestAlgorithm algorithm, Path path) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm.getAlgorithmName());
            try(InputStream fis = Files.newInputStream(path)) {
                byte[] buffer = new byte[8192]; // TODO: magic number
                int count = 0;
                while ((count = fis.read(buffer)) > 0) {
                    messageDigest.update(buffer, 0, count);
                }
                return messageDigest.digest();
            }
            catch (IOException e) {
                throw new CommonBusinessException(e);
            }
        }
        catch (NoSuchAlgorithmException e) {
            throw new CommonBusinessException(e);
        }
    }

    private String convertBytesToHex(byte[] bytesData) {
        StringBuilder builder = new StringBuilder(512);
        for (byte item : bytesData) {
            builder.append(String.format("%02X", item)); // notice the place of 02.
        }
        return builder.toString();
    }
}
