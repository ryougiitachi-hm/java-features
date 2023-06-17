package per.itachi.java.features.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.MDC;
import per.itachi.java.features.thread.common.ContextConstant;
import per.itachi.java.features.thread.common.ContextLogWrapper;

public class ContextThreadFactory implements ThreadFactory {

    private String threadPrefixName;

    private AtomicInteger threadNbr = new AtomicInteger(0);

    public ContextThreadFactory() {}

    public ContextThreadFactory(String threadPrefixName) {
        this.threadPrefixName = threadPrefixName;
    }

    @Override
    public Thread newThread(Runnable r) {
        ContextLogWrapper contextLogWrapper = new ContextLogWrapper();
        contextLogWrapper.setRequestId(MDC.get(ContextConstant.CONTEXT_NAME_X_REQUEST_ID));
        Runnable loggableRunable = () -> {
            try {
                // the field will be empty if put here
//                String requestId = MDC.get(ContextConstant.CONTEXT_NAME_X_REQUEST_ID);
                // setup context
                MDC.put(ContextConstant.CONTEXT_NAME_X_REQUEST_ID, contextLogWrapper.getRequestId());
                r.run();
            }
            finally {
                // teardown context
            }
        };
        Thread newThread = Executors.defaultThreadFactory().newThread(loggableRunable);
        if (threadPrefixName != null || threadPrefixName.length() > 0) {
            newThread.setName(threadPrefixName + "-" + threadNbr.incrementAndGet());
        }
        return newThread;
    }
}
