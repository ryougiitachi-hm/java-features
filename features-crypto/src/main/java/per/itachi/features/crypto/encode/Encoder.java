package per.itachi.features.crypto.encode;

public interface Encoder {

    String encode(String toEncodeData);

    String decode(String encodedData);
}
