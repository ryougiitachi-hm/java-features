package per.itachi.java.features.commons.beans;

import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;

import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

import per.itachi.java.features.commons.beans.entity.Person;

public class BeansApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(BeansApplication.class);

	public static void main(String[] args) {
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
			logger.info("addBeanInfo: ");
			if (beanInfo.getAdditionalBeanInfo() != null) {
				for (BeanInfo addBeanInfo : beanInfo.getAdditionalBeanInfo()) {
					logger.info("{}", addBeanInfo);
				}
			}
			logger.info("methodDescriptor: ");
			for (MethodDescriptor methodDescriptor : beanInfo.getMethodDescriptors()) {
				logger.info("methodDescriptor: {}", methodDescriptor);
			}
			logger.info("propertyDescriptor: ");
			for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
				logger.info("propertyDescriptor: {}", propertyDescriptor);
			}
			logger.info("eventSetDescriptor: ");
			for (EventSetDescriptor eventSetDescriptor : beanInfo.getEventSetDescriptors()) {
				logger.info("eventSetDescriptor: {}", eventSetDescriptor);
			}
		}
		catch (IntrospectionException e) {
			logger.error("", e);
		}
	}

}
