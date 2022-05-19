package per.itachi.java.features.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Entry {

    public static void main(String[] args) {
//        testJoin();
//        testYield();
        testTimeWait();
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

    private static void testTimeWait() {
        Object mutex = new Object();
        Thread thdTimeWait = new Thread(()->{
            synchronized (mutex) {
                try {
                    log.info("Thread {} acquired lock and waits {}. ", Thread.currentThread(), ~127);
                    // thread will continue to wait even if the time elapses
                    // after other thread acquired mutex.
                    // state is converted from TIMED_WAITING (on object monitor) to BLOCKED (on object monitor).
                    mutex.wait(9000L);mutex.notify();
//                    mutex.wait();
                    log.info("Thread {} waked up. ", Thread.currentThread());
                }
                catch (InterruptedException e) {
                    log.info("", e);
                }
            }
        }, "thread-time-wait");
        Thread thdSleep = new Thread(()->{
            try {
                // thread.state = TIMED_WAITING (sleeping)
                Thread.sleep(1000L);
            }
            catch (InterruptedException e) {
                log.info("", e);
            }
            synchronized (mutex) {
                try {
                    log.info("Thread {} acquired lock and starts sleeping. ", Thread.currentThread());
                    Thread.sleep(30 * 1000L);
                    log.info("Thread {} waked up. ", Thread.currentThread());
                }
                catch (InterruptedException e) {
                    log.info("", e);
                }
            }
        }, "thread-sleep");

        thdTimeWait.start();
        thdSleep.start();
    }

}
