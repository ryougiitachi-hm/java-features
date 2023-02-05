package per.itachi.features.crypto.digest;

import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DigestEntry {

    public static void main(String[] args) {
        DigestStrategySelector selector = new DigestStrategySelector();
        Path path = Paths.get(args[0]);
        log.info("Base64: ");
        for (DigestAlgorithm algorithm : DigestAlgorithm.values()) {
            String base64Digest = selector.digestAsBase64(algorithm, path);
            log.info("algorithm={}, digest={}. ", algorithm, base64Digest);
        }
        log.info("Hex: ");
        for (DigestAlgorithm algorithm : DigestAlgorithm.values()) {
            String strHexDigest = selector.digestAsHex(algorithm, path);
            log.info("algorithm={}, hex={}. ", algorithm, strHexDigest);
        }
    }

}
