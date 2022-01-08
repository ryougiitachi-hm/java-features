package per.itachi.java.features.forkjoin.sort;

public class SortUtils {
	
	public static int[] sortByQuick(int[] array) {
		return sortByQuick(array, 0, array.length - 1);
	}
	
	public static int[] sortByQuick(int[] array, int start, int end) {
		if (start < end) {
			int pivot = getPivotByPartition(array, start, end);
			sortByQuick(array, start, pivot - 1);
			sortByQuick(array, pivot + 1, end);
		}
		return array;
	}
	
	public static int getPivotByPartition(int[] array, int start, int end) {
		int key = array[start];
		int low = start, high = end;
		while (low < high) {
			while (low < high && key <= array[high]) {
				--high;
			}
			array[low] = array[high];
			while (low < high && array[low] <= key ) {
				++low;
			}
			array[high] = array[low];
		}
		// low == high
		array[low] = key;
		return low;
	}

}
