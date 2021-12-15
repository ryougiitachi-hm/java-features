package per.itachi.java.features.locks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.LockSupport;

public class EntryLockSupport {

    private static final Logger logger = LoggerFactory.getLogger(EntryLockSupport.class);

    public static void main(String[] args) {
        showCurrentThreadBlocker();
//        park();
//        parkNanos();
//        parkUntil();
        unpark();
    }

    private static void showCurrentThreadBlocker() {
        Thread thread = Thread.currentThread();
        // When blocker is not set via park/parkNanos/parkUntil, the blocker is null.
        logger.info("The current thread is {}, and the blocker is {}. ",
                thread, LockSupport.getBlocker(thread));
    }

    private static void park() {
        logger.info("Invoking LockSupport.park(). ");
        LockSupport.park();// thread.state=WAITING(parking);
    }

    private static void parkNanos() {
        long waitNanos = 3600123456789L;//
        logger.info("Invoking LockSupport.parkNanos() for {}ns. ", waitNanos);
        LockSupport.parkNanos(waitNanos); // thread.state=TIMED_WAITING(parking)
    }

    private static void parkUntil() {
        long waitMillsUntil = System.currentTimeMillis() + 3600123L;
        logger.info("Invoking LockSupport.parkNanos() until {}ms. ", waitMillsUntil);
        LockSupport.parkUntil(waitMillsUntil); // thread.state=TIMED_WAITING(parking)
    }

    private static void unpark() {
        Thread thread = Thread.currentThread();
        logger.info("Invoking LockSupport.unpark() for {}. ", thread);
        LockSupport.unpark(thread); // return normally
    }
}
