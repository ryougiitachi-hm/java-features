package per.itachi.java.features.beans.app.helper.beans;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class BeanInfoHelper {

    private ConcurrentMap<Class<?>, BeanInfoWrapper> handled;

//    @PostConstruct
    public void init() {
        handled = new ConcurrentHashMap<>();
    }

    public <T> T getBeanPropertyValue(Object obj, String propertyName, Class<T> clazz) {
        BeanInfoWrapper wrapper = handled.get(obj.getClass());
        if (wrapper == null) {
            // init
        }
        if (!wrapper.hasReadMethod(propertyName)) {
            return null;
        }
        Object objProperty = wrapper.getPropertyValue(obj, propertyName);
        return clazz.cast(objProperty);
    }
}
