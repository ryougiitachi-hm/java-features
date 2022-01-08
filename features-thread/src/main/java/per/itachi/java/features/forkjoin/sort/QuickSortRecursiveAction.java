package per.itachi.java.features.forkjoin.sort;

import java.util.concurrent.RecursiveAction;

public class QuickSortRecursiveAction extends RecursiveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int[] array;
	
	private int start;
	
	private int end;
	
	public QuickSortRecursiveAction(int[] array, int start, int end) {
		this.array = array;
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {
		if(start < end) {
			int pivot = SortUtils.getPivotByPartition(array, start, end);
			RecursiveAction actionLeft = new QuickSortRecursiveAction(array, start, pivot - 1);
			RecursiveAction actionRight = new QuickSortRecursiveAction(array, pivot + 1, end);
			actionLeft.fork();
			actionRight.fork();
		}
	}

}
