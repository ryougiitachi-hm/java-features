package per.itachi.java.features.beans.app.helper.beans;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import lombok.SneakyThrows;

/**
 * maybe not necessary
 * */
public class BeanInfoWrapper {

    private Map<String, BeanInfoProperty> properties = new HashMap<>();

    public boolean hasReadMethod(String propertyName) {
        BeanInfoProperty property = properties.get(propertyName);
        return property.getReadMethod() != null;
    }

    public boolean hasWriteMethod(String propertyName) {
        BeanInfoProperty property = properties.get(propertyName);
        return property.getReadMethod() != null;
    }

    @SneakyThrows
    public Object getPropertyValue(Object bean, String propertyName) {
        BeanInfoProperty property = properties.get(propertyName);
        Method method = property.getReadMethod();
        return method.invoke(bean);
    }

    @SneakyThrows
    public void setPropertyValue(Object bean, String propertyName, Object value) {
        BeanInfoProperty property = properties.get(propertyName);
        Method method = property.getWriteMethod();
        method.invoke(bean, value);
    }
}
