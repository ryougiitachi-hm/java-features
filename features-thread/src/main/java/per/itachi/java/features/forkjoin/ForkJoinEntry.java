package per.itachi.java.features.forkjoin;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ThreadLocalRandom;

import per.itachi.java.features.forkjoin.sort.QuickSortRecursiveAction;
import per.itachi.java.features.forkjoin.sort.SortUtils;

public class ForkJoinEntry {
	
	public int[] generateArray(int count) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		int[] array = new int[count];
		for (int i = 0; i < count; i++) {
			array[i] = random.nextInt(100);
		}
		return array;
	}
	
	public void traverseArray(int[] array) {
		System.out.println("The content of array is: ");
		for (int i : array) {
			System.out.printf("%d ", i);
		}
		System.out.println();
	}
	
	public void testSortBySingle(int[] array, int times, boolean traverse) {
		if (traverse) {
			traverseArray(array);
		}
		long startPoint = System.nanoTime();
		for (int i = 0; i < times; i++) {
			array = SortUtils.sortByQuick(array.clone());
//			SortUtils.sortByQuick(array);
		}
		System.out.printf("The testSortBySingle cost %dns by %d numbers and %d times. %n", 
				System.nanoTime() - startPoint, array.length, times);
		if (traverse) {
			traverseArray(array);
		}
	}
	
	/**
	 * The more data and more times, the better performance ForkJoin has. 
	 * */
	public void testSortByForkJoin(int[] array, int times, boolean traverse) {
		if (traverse) {
			traverseArray(array);
		}
		long startPoint = System.nanoTime();
		Queue<ForkJoinTask<Void>> queue = new LinkedList<ForkJoinTask<Void>>();
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		ForkJoinTask<Void> task;
		try {
			for (int i = 0; i < times; i++) {
				task = forkJoinPool.submit(new QuickSortRecursiveAction(array.clone(), 0, array.length - 1));
				queue.add(task);
			}
			for (ForkJoinTask<Void> item : queue) {
				item.get();
			}
		} 
		catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.printf("The testSortByForkJoin cost %dns by %d numbers and %d times. %n", 
				System.nanoTime() - startPoint, array.length, times);
		if (traverse) {
			traverseArray(array);
		}
	}

	public static void main(String[] args) {
		ForkJoinEntry entry = new ForkJoinEntry();
		int num = 10;
		int times = 100000;
		int[] array = entry.generateArray(num);
		entry.traverseArray(array);
		entry.testSortBySingle(array, times, false);
		entry.testSortByForkJoin(array, times, false);
//		The testSortBySingle   cost 532400ns by 1 numbers and 1 times. 
//		The testSortByForkJoin cost 1635500ns by 1 numbers and 1 times. 
//		The testSortBySingle   cost 670000ns by 300 numbers and 1 times. 
//		The testSortByForkJoin cost 1911700ns by 300 numbers and 1 times. 
//		The testSortBySingle   cost 434000ns by 30 numbers and 10 times. 
//		The testSortByForkJoin cost 1903100ns by 30 numbers and 10 times. 
//		The testSortBySingle   cost 17908400ns by 10 numbers and 100000 times. 
//		The testSortByForkJoin cost 74238600ns by 10 numbers and 100000 times. 
//		The testSortBySingle   cost 26131939700ns by 300 numbers and 1000000 times. 
//		The testSortByForkJoin cost 5257118300ns by 300 numbers and 1000000 times. 
	}

}
