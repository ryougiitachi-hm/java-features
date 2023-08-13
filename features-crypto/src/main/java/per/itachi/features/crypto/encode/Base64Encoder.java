package per.itachi.features.crypto.encode;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Encoder implements Encoder {

    @Override
    public String encode(String toEncodeData) {
        return Base64.getEncoder().encodeToString(toEncodeData.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String decode(String encodedData) {
        byte[] bytesDecodedData = Base64.getDecoder().decode(encodedData);
        return new String(bytesDecodedData);
    }
}
