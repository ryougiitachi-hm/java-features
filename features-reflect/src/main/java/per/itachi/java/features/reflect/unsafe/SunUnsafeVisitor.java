package per.itachi.java.features.reflect.unsafe;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/**
 * sun.misc.Unsafe
 * */
public class SunUnsafeVisitor implements UnsafeVisitor {

    private Unsafe unsafe;

    public SunUnsafeVisitor() throws IllegalAccessException {
        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        this.unsafe = (Unsafe) field.get(null);
    }

    @Override
    public int getInt(Object obj, long offset) {
        return this.unsafe.getInt(obj, offset);
    }

    @Override
    public void putInt(Object obj, long offset, int value) {
        this.unsafe.putInt(obj, offset, value);
    }

    @Override
    public int getIntVolatile(Object obj, long offset) {
        return this.unsafe.getIntVolatile(obj, offset);
    }

    @Override
    public void putIntVolatile(Object obj, long offset, int value) {
        this.unsafe.putInt(obj, offset, value);
    }

    @Override
    public void park(boolean isAbsolute, long timeout) {
        this.unsafe.park(isAbsolute, timeout);
    }

    @Override
    public void unpark(Object thread) {
        this.unsafe.unpark(thread);
    }

    @Override
    public void monitorEnter(Object mutex) {
//        this.unsafe.monitorEnter(mutex); // doesn't exist
    }

    @Override
    public void monitorExit(Object mutex) {
//        this.unsafe.monitorExit(mutex); // doesn't exist
    }

    @Override
    public boolean tryMonitorEnter(Object mutex) {
//       return this.unsafe.tryMonitorEnter(mutex); // doesn't exist
        return false;
    }

}
