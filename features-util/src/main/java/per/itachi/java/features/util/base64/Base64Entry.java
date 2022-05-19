package per.itachi.java.features.util.base64;

import java.util.Base64;

public class Base64Entry {

    public static void main(String[] args) {
        String srcBase64 = args[0];
        byte[] data = Base64.getDecoder().decode(srcBase64);
        String desData = new String(data);
        System.out.printf("%s %n", desData);
    }
}