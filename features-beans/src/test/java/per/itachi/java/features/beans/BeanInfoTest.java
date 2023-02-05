package per.itachi.java.features.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import per.itachi.java.features.beans.joint.controller.vo.UserVO;

@Slf4j
public class BeanInfoTest {

    @Test
    public void testBeanInfo() {
        try {
            BeanInfo beanInfoUserVO = Introspector.getBeanInfo(UserVO.class);
            PropertyDescriptor[] propertyDescriptors = beanInfoUserVO.getPropertyDescriptors();
            for (PropertyDescriptor item : propertyDescriptors) {
                log.info("item={}, readMethod={}, writeMethod={}, item.value={}",
                        item.getName(), item.getReadMethod(), item.getWriteMethod(), item.getValue(item.getName()));
            }
        }
        catch (IntrospectionException e) {
            log.error("", e);
        }
    }
}
