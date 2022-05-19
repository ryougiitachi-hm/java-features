package per.itachi.java.features.log4j2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Log4j2Entry {

    public static void main(String[] args) {
        log.info("args is {}", args[0]);
    }
}
