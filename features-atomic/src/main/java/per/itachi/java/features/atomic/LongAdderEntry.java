package per.itachi.java.features.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.LongAdder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LongAdderEntry {

    public static final void main(String[] args) {
        LongAdderInvoker invoker = new LongAdderInvoker();
        invoker.testBasicOps();
//        invoker.accumulateThousandsTimes();
    }
}

@Slf4j
class LongAdderInvoker {

    private static final int TIMES_OF_ACCUMULATE = 10000;

    public void testBasicOps() {
        log.info("Start testing basic ops for LongAdder. ");
        LongAdder longAdder = new LongAdder();
        longAdder.increment(); // ++i
        log.info("longAdder.increment is {}", longAdder);
        longAdder.decrement(); // --i
        log.info("longAdder.decrement is {}", longAdder);
        longAdder.add(100L); // += 100
        log.info("longAdder.add is {}", longAdder);
        longAdder.reset(); // = 0
        log.info("longAdder.reset is {}", longAdder);
        longAdder.add(100L);
        log.info("longAdder.sumThenReset is {}", longAdder.sumThenReset()); // return and =0
        log.info("longAdder.sumThenReset is {}", longAdder); // 0
    }

    public void accumulateThousandsTimes() {
        log.info("Start testing accumulation of long adder. ");
        int countOfThreads = 10;
        CountDownLatch latch = new CountDownLatch(countOfThreads);
        LongAdder longAdder = new LongAdder();
        Runnable runnable = ()-> {
            long startPoint = System.nanoTime();
            for (int i = 0; i < TIMES_OF_ACCUMULATE; ++i) {
                longAdder.increment();
            }
            // 3893400ns 3275700ns 2766700ns 2157000ns 3898500ns 3465400ns 677000ns 5890800ns 648900ns 391300ns
            log.info("The thread {} spent {}ns", Thread.currentThread(), System.nanoTime() - startPoint);
            latch.countDown();
        };
        for (int i = 0; i < countOfThreads; ++i) {
            log.info("Initializing thread {}. ", i);
            Thread thread = new Thread(runnable, String.format("thread-LongAdder-%02d", i));
            thread.start();
        }

        try {
            latch.await();
            log.info("The result of LongAdder is {}. ", longAdder);
        }
        catch (InterruptedException e) {
            log.error("", e);
        }
    }
}
