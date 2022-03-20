package per.itachi.java.features.schedule;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorEntry {

	public static void main(String[] args) {
		int corePoolSize = 2;
		ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(corePoolSize);
		
		// executes once
		executor.schedule(()->{
			System.out.printf("schedule %d. %n", System.currentTimeMillis());
		}, 2, TimeUnit.SECONDS);
		
		// executes regularly
		executor.scheduleAtFixedRate(()->{
			System.out.printf("scheduleAtFixedRate %d. %n", System.currentTimeMillis());
		}, 5, 2, TimeUnit.SECONDS);
		
		// executes regularly
		executor.scheduleWithFixedDelay(()->{
			System.out.printf("scheduleWithFixedDelay %d. %n", System.currentTimeMillis());
		}, 5, 2, TimeUnit.SECONDS);
	}

}
