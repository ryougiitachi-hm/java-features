package per.itachi.java.features.number;

import java.util.concurrent.ThreadLocalRandom;

public class IntegerEntry {

    public static void main(String[] args) {
        int i = ThreadLocalRandom.current().nextInt();
        int iNumberOfLeadingZeros = Integer.numberOfLeadingZeros(i);
        // The result of Integer.numberOfLeadingZeros(66640189) 03F8D93D is 6.
        System.out.printf("The result of Integer.numberOfLeadingZeros(%d) %08X is %d. ",
                i, i, iNumberOfLeadingZeros);
    }

}
