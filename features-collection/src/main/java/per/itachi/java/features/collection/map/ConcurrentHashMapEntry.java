package per.itachi.java.features.collection.map;

public class ConcurrentHashMapEntry {

    public static void main(String[] args) {
        testTableSizeFor();
    }

    private static void testTableSizeFor() {
        int iInput = 156;
        int iOutput = rawTableSizeFor(iInput);
        System.out.printf("The usage of tableSizeFor(%d) is %d. ", iInput, iOutput);
    }

    private static int rawTableSizeFor(int c) {
        int n = c - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= 1 << 30) ? (1 << 30) : n + 1;
    }

}
