package per.itachi.java.features.atomic;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EntryAtomic {

    public static void main(String[] args) {
        AtomicIntegerInvoker atomicIntegerInvoker = new AtomicIntegerInvoker();
        LongAdderEntry longAdderInvoker = new LongAdderEntry();
//        invoker.test();
        atomicIntegerInvoker.accumulateThousandsTimesByNorm();
        atomicIntegerInvoker.accumulateThousandsTimes();
//        longAdderInvoker.accumulateThousandsTimes();
    }

}
