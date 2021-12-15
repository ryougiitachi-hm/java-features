package per.itachi.java.features.locks;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class EntryReentranceLock {

    public static void main(String[] args) {
        unlockWithoutAcquire();
    }

    private static void unlockWithoutAcquire() {
        Lock lock = new ReentrantLock();
        lock.unlock();// throws IllegalMonitorStateException because of unaquiring lock.
    }
}
