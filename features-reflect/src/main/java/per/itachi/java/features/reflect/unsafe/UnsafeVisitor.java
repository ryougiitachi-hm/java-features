package per.itachi.java.features.reflect.unsafe;

public interface UnsafeVisitor {

    //************************* read/write normally *************************

    int getInt(Object obj, long address);

    void putInt(Object obj, long address, int value);

    //************************* read/write volatile *************************

    int getIntVolatile(Object obj, long address);

    void putIntVolatile(Object obj, long address, int value);

    //************************* thread related *************************

    void park(boolean var1, long timeout);

    void unpark(Object thread);

    void monitorEnter(Object mutex);

    void monitorExit(Object mutex);

    boolean tryMonitorEnter(Object mutex);
}
