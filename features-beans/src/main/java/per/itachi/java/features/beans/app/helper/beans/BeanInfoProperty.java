package per.itachi.java.features.beans.app.helper.beans;

import java.lang.reflect.Method;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BeanInfoProperty {

    private String propertyName;

    private Class<?> propertyType;

    private Method readMethod;

    private Method writeMethod;
}
