package per.itachi.features.crypto.encode;

public class EncodeEntry {

    public static void main(String[] args) {
        String strEncodedData = args[0];
        Encoder encoder = new Base64Encoder();
        String strDecodedData = encoder.decode(strEncodedData);
        System.out.printf("strDecodedData=%s %n", strDecodedData);
    }
}
