package per.itachi.java.features.commons.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 为了验证之前的一个疑问，即锁*.class对象，对应的实例对象是否也会被锁；
 * 经试验证明，不会，*.class和对应的类实例对象是不同的对象，synchroinzed只会识别类对象，而不会在意是否是其实例；
 * 之前想太多；
 * */
public class ClassSyncApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(ClassSyncApplication.class);

	public static void main(String[] args) {
		Object obj = new Object(); 
		
		Thread thdLockClass = new Thread(() -> {
			try {
				logger.info("thd-Lock-Class start");
				synchronized (Object.class) {
					logger.info("thd-Lock-Class acquired Object.class {} {}. ", 
							Object.class, String.format("%08x", System.identityHashCode(Object.class)));
					Thread.sleep(10000l);
				}
				logger.info("thd-Lock-Class released Object.class. ");
			} 
			catch (InterruptedException e) {
				logger.error("", e);
			}
		}, "lock-class");
		
		Thread thdLockObject = new Thread(() -> {
			logger.info("thd-Lock-Object start");
			try {
				Thread.sleep(1000l);
				logger.info("thd-Lock-Object released obj. ");
			} 
			catch (InterruptedException e) {
				logger.error("", e);
			}
			synchronized (obj) {// 正常被线程获取；
				logger.info("thd-Lock-Object acquired obj {}. ", obj);
			}
		}, "lock-object");
		
		thdLockClass.start();
		thdLockObject.start();
		
	}

}
