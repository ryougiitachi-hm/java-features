package per.itachi.java.features.locks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntrySynchronized {

    private static final Logger logger = LoggerFactory.getLogger(EntrySynchronized.class);

    public static void main(String[] args) {
        testSynchronized();
//        testWhetherClassLocksObject();
    }

    private static void testSynchronized() {
        PseudoMutex mutex = new PseudoMutex();
        // Duplicated thread name won't cause exception, but is not easy to identify.
        Thread thread1 = new Thread(createRunnableForObject(mutex), "testSynchronized-thread1");
        Thread thread2 = new Thread(createRunnableForMethod(mutex), "testSynchronized-thread2");
        Thread thread3 = new Thread(createRunnableForMethod(mutex), "testSynchronized-thread3");
        thread1.start();
        thread2.start();
        thread3.start();
    }

    private static void testWhetherClassLocksObject() {
        PseudoMutex mutex = new PseudoMutex();
        // Duplicated thread name won't cause exception, but is not easy to identify.
        Thread thread1 = new Thread(createRunnableForObject(mutex), "testSynchronized-thread1");
        Thread thread2 = new Thread(createRunnableForClass(mutex), "testSynchronized-thread2");
        Thread thread3 = new Thread(createRunnableForStaticMethod(mutex), "testSynchronized-thread3");
        // The static synchronized method has no effect on instantiation mutex, but synchronized Object.class.
        thread1.start();
        thread2.start();
        thread3.start();
    }

    private static Runnable createRunnableForObject(PseudoMutex mutex) {
        return () -> {
            logger.info("The thread {} is trying to acquire {}. ", Thread.currentThread(), mutex);
            mutex.synchronizeObject();
        };
    }

    private static Runnable createRunnableForMethod(PseudoMutex mutex) {
        return () -> {
            logger.info("The thread {} is trying to acquire {}. ", Thread.currentThread(), mutex);
            mutex.synchronizedMethod();
        };
    }

    private static Runnable createRunnableForClass(PseudoMutex mutex) {
        return () -> {
            logger.info("The thread {} is trying to acquire {}. ", Thread.currentThread(), mutex);
            mutex.synchronizedClass();
        };
    }

    private static Runnable createRunnableForStaticMethod(PseudoMutex mutex) {
        return () -> {
            logger.info("The thread {} is trying to acquire static method. ", Thread.currentThread());
            PseudoMutex.synchronizedStaticMethod();
        };
    }

}

class PseudoMutex {

    private static Logger logger = LoggerFactory.getLogger(PseudoMutex.class);

//    private Object mutex = new Object();

    public void synchronizeObject() {
        synchronized (this) {
            // thread.state=BLOCKED (on object monitor)
            logger.info("The thread {} acquired {}. ", Thread.currentThread(), this);
            try {
                wait(); // thread.state=WAITING (on object monitor)
            }
            catch (InterruptedException e) {
                logger.error("", e);
            }
            sleep();
        }
    }

    public synchronized void synchronizedMethod() {
        logger.info("The thread {} acquired {}. ", Thread.currentThread(), this);
        try {
            wait();
        }
        catch (InterruptedException e) {
            logger.error("", e);
        }
        sleep();
    }

    public void synchronizedClass() {
        synchronized (PseudoMutex.class) {
            logger.info("The thread {} acquired {}. ", Thread.currentThread(), PseudoMutex.class);
            sleep();
        }
    }

    public static synchronized void synchronizedStaticMethod() {
        logger.info("The thread {} acquired static method. ", Thread.currentThread());
        sleep();
    }

    private static void sleep() {
        try {
            // thread.state=TIMED_WAITING (sleeping)
            Thread.sleep(3600123L);
        }
        catch (InterruptedException e) {
            logger.error("", e);
        }
    }

}
