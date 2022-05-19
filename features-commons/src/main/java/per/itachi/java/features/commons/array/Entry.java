package per.itachi.java.features.commons.array;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Entry {

    public static void main(String[] args) {
        Object[] arrays = new Object[] {new Object(), new Object()};
        log.info("The array of objects is {}. ", arrays);
    }
}
