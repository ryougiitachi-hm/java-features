package per.itachi.java.features.locks;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.StampedLock;

@Slf4j
class StampedLockInvoker {

    public void testBlocking() {
        StampedLock stampedLock = new StampedLock();
        Runnable runnableWrite = () -> {
            // blocks if fails to acquire.
            long stamp = stampedLock.writeLock();
            log.info("Thread {} has stamp {}, this={}. ",
                    Thread.currentThread(), stamp, this);
        };
        Thread thdWrite1 = new Thread(runnableWrite, "write-1");
        Thread thdWrite2 = new Thread(runnableWrite, "write-2");
        thdWrite1.start();
        thdWrite2.start();
    }
}

public class StampedLockEntry {

    public static void main(String[] args) {
        StampedLockInvoker invoker = new StampedLockInvoker();
        invoker.testBlocking();
    }
}
