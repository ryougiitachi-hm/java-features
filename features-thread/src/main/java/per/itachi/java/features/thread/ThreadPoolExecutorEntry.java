package per.itachi.java.features.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolExecutorEntry {

	public static void main(String[] args) {
		int corePoolSize = Runtime.getRuntime().availableProcessors();
		BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(10);
		ExecutorService executorService = new ThreadPoolExecutor(corePoolSize, corePoolSize, 
				0, null, workQueue);
		executorService.submit(()->{
			System.out.printf("submit %d %n", System.currentTimeMillis());
		});
	}

}
