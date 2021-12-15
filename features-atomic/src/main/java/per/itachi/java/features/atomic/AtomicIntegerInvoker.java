package per.itachi.java.features.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class AtomicIntegerInvoker {

    private static final int TIMES_OF_ACCUMULATE = 10000;

    private int count;

    public void test() {
        AtomicInteger atomicInteger = new AtomicInteger();
        log.info("AtomicInteger init is {}. ", atomicInteger);
//        atomicInteger.accumulateAndGet()
    }

    public void accumulateThousandsTimes() {
        log.info("Start testing accumulation of atomic integer. ");
        int countOfThreads = 10;
        CountDownLatch latch = new CountDownLatch(countOfThreads);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Runnable runnable = ()-> {
            long startPoint = System.nanoTime();
            for (int i = 0; i < TIMES_OF_ACCUMULATE; ++i) {
                atomicInteger.incrementAndGet();
            }
            // 788500ns 263400ns 252500ns 569200ns 329100ns 582400ns 470400ns 835000ns 5924700ns 1308400ns
            log.info("The thread {} spent {}ns", Thread.currentThread(), System.nanoTime() - startPoint);
            latch.countDown();
        };
        for (int i = 0; i < countOfThreads; ++i) {
            log.info("Initializing thread {}. ", i);
            Thread thread = new Thread(runnable, String.format("thread-AtomicInteger-%02d", i));
            thread.start();
        }

        try {
            latch.await();
            log.info("The result of atomicInteger is {}. ", atomicInteger);
        }
        catch (InterruptedException e) {
            log.error("", e);
        }
    }

    public void accumulateThousandsTimesByNorm() {
        log.info("Start testing accumulation of normal variable. ");
        int countOfThreads = 10;
        CountDownLatch latch = new CountDownLatch(countOfThreads);
        Runnable runnable = ()-> {
            long startPoint = System.nanoTime();
            for (int i = 0; i < TIMES_OF_ACCUMULATE; ++i) {
                ++count;
            }
            // 318200ns 356800ns 745500ns 636400ns 740400ns 904300ns 834500ns 414200ns 711700ns 689800ns
            log.info("The thread {} spent {}ns", Thread.currentThread(), System.nanoTime() - startPoint);
            latch.countDown();
        };
        for (int i = 0; i < countOfThreads; ++i) {
            log.info("Initializing thread {}. ", i);
            Thread thread = new Thread(runnable, String.format("thread-int-%02d", i));
            thread.start();
        }

        try {
            latch.await();
            log.info("The result of count is {}. ", count);
        }
        catch (InterruptedException e) {
            log.error("", e);
        }
    }

}
