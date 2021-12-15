package per.itachi.java.features.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Entry {

    public static void main(String[] args) {
        testJoin();
//        testYield();
    }

    private static void testJoin() {
        try {
            Thread threadA = new Thread(() -> {
                log.info("Thread {} is executing task. {}", Thread.currentThread());
            }, "Thread A");
            threadA.start();
            log.info("Thread {} is waiting for joining. ", Thread.currentThread());
            threadA.join();
            // this log will be output after thread A becomes not alive(finished).
            log.info("Thread {} finished joining. ", Thread.currentThread());
        }
        catch (InterruptedException e) {
            log.error("", e);
        }
    }

    private static void testYield() {
//        testYield();
        log.info("Thread {} is yielding. ", Thread.currentThread());
        Thread.yield();// won't block and continue holding lock.
    }

}
