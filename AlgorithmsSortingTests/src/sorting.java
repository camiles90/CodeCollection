
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
import java.util.Random;
import java.util.Arrays;

public class sorting {

	private static int[] arr;
	private static int[] arrCopy;
	private static int[] arrCopy2;
	//private static BufferedReader read;
	private static Random randomGenerator;

	private static int size = 1000000;
	private static int random = 10000;

	private static int n;

	private static void printArray(String msg) {
		System.out.print(msg + " [" + arr[0]);
		for (int i = 1; i < size; i++) {
			System.out.print(", " + arr[i]);
		}
		System.out.println("]");
	}
	
	//BottumUp Merge Sort from give psuedocode
	public static void bottumUpMergeSort(){
		int t =1;
		while(t<n){
			int s = t;
			t = 2*s;
			int i =0;
			while((i+t)<= n){
				merge(i+1,i+s,i+t);
				i = i + t;
			}
			if((i+s) < n){
				merge(i+1,i+s,n);
			}
		}
	}
	

	public static void insertSort(int left, int right) {
		// insertSort the subarray a[left, right]
		int i, j;

		for (i = left + 1; i <= right; i++) {
			int temp = arr[i]; // store a[i] in temp
			j = i; // start shifts at i
			// until one is smaller,
			while (j > left && arr[j - 1] >= temp) {
				arr[j] = arr[j - 1]; // shift item to right
				--j; // go left one position
			}
			arr[j] = temp; // insert stored item
		} // end for
	} // end insertSort()

	public static void insertionSort() {
		insertSort(0, size - 1);
	} // end insertionSort()

	public static void buildheap() {
		// Build an in-place bottom up
		n = size - 1;
		for (int i = n / 2; i >= 0; i--) {
			heapify(i);
		}
	}

	public static void heapify(int i) {
		// Assuming left and right subtrees are heaps.
		// Check if node i is the largest element.
		int largest;
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		if (left <= n && arr[left] > arr[i]) {
			largest = left;
		} else {
			largest = i;
		}

		if (right <= n && arr[right] > arr[largest]) {
			largest = right;
		}
		if (largest != i) {
			exchange(i, largest);
			heapify(largest);
		}
	}

	public static void exchange(int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

	public static void heapsort() {
		buildheap();
		for (int i = n; i > 0; i--) {
			exchange(0, i);
			n = n - 1;
			heapify(0);
		}
	}

	private static void mergesort(int low, int high) {
		// Check if low is smaller then high, if not then the array is sorted
		if (low < high) {
			if(arr.length < 100){
				insertSort(low,high);
			}
			// Get the index of the element which is in the middle
			int middle = low + (high - low) / 2;
			// Sort the left side of the array
			if(isSorted(low,middle)==false){
				mergesort(low, middle);	
			}
			// Sort the right side of the array
			if(isSorted(middle+1,high)==false){
				mergesort(middle + 1, high);
			}
			// Combine them both
			merge(low, middle, high);
		}
	}

	private static void merge(int low, int middle, int high) {

		// Copy both parts into the arrCopy array
		for (int i = low; i <= high; i++) {
			arrCopy2[i] = arr[i];
		}

		int i = low;
		int j = middle + 1;
		int k = low;

		// Copy the smallest values from either the left or the right side back
		// to the original array
		while (i <= middle && j <= high) {
			if (arrCopy2[i] <= arrCopy2[j]) {
				arr[k] = arrCopy2[i];
				i++;
			} else {
				arr[k] = arrCopy2[j];
				j++;
			}
			k++;
		}

		// Copy the rest of the left side of the array into the target array
		while (i <= middle) {
			arr[k] = arrCopy2[i];
			k++;
			i++;
		}

	}

	private static void quicksort(int low, int high) {
		int i = low, j = high;
		if(arr.length < 100){
			insertSort(low,high);
		}

		// Get the pivot element from the middle of the list
		int pivot = arr[(high + low) / 2];

		// Divide into two lists
		while (i <= j) {
			// If the current value from the left list is smaller then the pivot
			// element then get the next element from the left list
			while (arr[i] < pivot) {
				i++;
			}
			// If the current value from the right list is larger then the pivot
			// element then get the next element from the right list
			while (arr[j] > pivot) {
				j--;
			}

			// If we have found a value in the left list which is larger then
			// the pivot element and if we have found a value in the right list
			// which is smaller then the pivot element then we exchange the
			// values.
			// As we are done we can increase i and j
			if (i < j) {
				exchange(i, j);
				i++;
				j--;
			} else if (i == j) {
				i++;
				j--;
			}
		}

		// Recursion
		if(isSorted(low,j)==false){
			if (low < j)
				quicksort(low, j);
		}
		if(isSorted(i,high)==false){
			if (i < high)
				quicksort(i, high);
		}
		
	}
	private static void quicksorta(int low, int high) {
		int i = low, j = high;
		if(arr.length < 100){
			insertSort(low,high);
		}

		// Get the pivot element from the middle of the list
		int pivot = arr[(high + low) / 2];
		int pivot2 = arr[((high + low) / 2)+1];
		int pivot3 = arr[((high + low) / 2)-1];
		if(pivot >= pivot2 && pivot >= pivot3){
			if(pivot2>=pivot3){
				pivot = pivot2;
			}else{
				pivot = pivot3;
			}
		}
		if(pivot <= pivot2 && pivot <= pivot3){
			if(pivot2<=pivot3){
				pivot = pivot2;
			}else{
				pivot = pivot3;
			}
		}

		// Divide into two lists
		while (i <= j) {
			// If the current value from the left list is smaller then the pivot
			// element then get the next element from the left list
			while (arr[i] < pivot) {
				i++;
			}
			// If the current value from the right list is larger then the pivot
			// element then get the next element from the right list
			while (arr[j] > pivot) {
				j--;
			}

			// If we have found a value in the left list which is larger then
			// the pivot element and if we have found a value in the right list
			// which is smaller then the pivot element then we exchange the
			// values.
			// As we are done we can increase i and j
			if (i < j) {
				exchange(i, j);
				i++;
				j--;
			} else if (i == j) {
				i++;
				j--;
			}
		}

		// Recursion
		if(isSorted(low,j)==false){
			if (low < j)
				quicksort(low, j);
		}
		if(isSorted(i,high)==false){
			if (i < high)
				quicksort(i, high);
		}
		
	}
	private static void quicksortb(int low, int high) {
		int i = low, j = high;
		if(arr.length < 100){
			insertSort(low,high);
		}

		// Get the pivot element from the middle of the list
		int pivot = arr[(high + low) / 2];//median 2
		if(arr[(high + low)/4]<= pivot && arr[((high + low)*3)/4] <= pivot){
			if(arr[(high + low)/4]>= arr[((high + low)*3)/4]){
				pivot = arr[(high + low)/4];//median 1
			}
			else{
				pivot = arr[((high + low)*3)/4];//Median 3
			}
		}
		if(arr[(high + low)/4]>= pivot && arr[((high + low)*3)/4] >= pivot){
			if(arr[(high + low)/4]<= arr[((high + low)*3)/4]){
				pivot = arr[(high + low)/4];
			}
			else{
				pivot = arr[((high + low)*3)/4];
			}
		}

		// Divide into two lists
		while (i <= j) {
			// If the current value from the left list is smaller then the pivot
			// element then get the next element from the left list
			while (arr[i] < pivot) {
				i++;
			}
			// If the current value from the right list is larger then the pivot
			// element then get the next element from the right list
			while (arr[j] > pivot) {
				j--;
			}

			// If we have found a value in the left list which is larger then
			// the pivot element and if we have found a value in the right list
			// which is smaller then the pivot element then we exchange the
			// values.
			// As we are done we can increase i and j
			if (i < j) {
				exchange(i, j);
				i++;
				j--;
			} else if (i == j) {
				i++;
				j--;
			}
		}

		// Recursion
		if(isSorted(low,j)==false){
			if (low < j)
				quicksort(low, j);
		}
		if(isSorted(i,high)==false){
			if (i < high)
				quicksort(i, high);
		}
		
	}
	private static boolean isSorted(int low, int high){
		for(int i = low; i<high; i++){
			if(arr[i] > arr[i+1]){
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {

		//read = new BufferedReader(new InputStreamReader(System.in));

		randomGenerator = new Random();

		try {
			for(int m = 0; m <10; m++){
				//System.out.print("Please enter array size : ");
				//size = Integer.parseInt(read.readLine());

				//System.out.print("Please enter the random range : \n");
				//random = Integer.parseInt(read.readLine());
				System.out.println("Trial "+(m+1));
				// create array
				arr = new int[size];
				arrCopy = new int[size];
				arrCopy2 = new int[size];

				// fill array
				for (int i = 0; i < size; i++) {
					arr[i] = arrCopy[i] = randomGenerator.nextInt(random);
				}
				if (size < 101)
					printArray("Initial array:");

				long start = System.currentTimeMillis();
				Arrays.sort(arr);
				if (size < 101)
					printArray("out");
				long finish = System.currentTimeMillis();
				System.out.println("Arrays.sort: " + (finish - start) + " milliseconds.");
	
				// Heap sort
				for (int i = 0; i < size; i++)
					arr[i] = arrCopy[i];
				start = finish;
				if (size < 101)
					printArray("in");
				heapsort();
				if (size < 101)
					printArray("out");
				finish = System.currentTimeMillis();
				System.out.println("heapsort: " + (finish - start) + " milliseconds.");

				// Merge sort
				for (int i = 0; i < size; i++)
					arr[i] = arrCopy[i];
				start = finish;
				if (size < 101)
					printArray("in");
				mergesort(0, size - 1);
				if (size < 101)
					printArray("out");
				finish = System.currentTimeMillis();
				System.out.println("mergesort: " + (finish - start) + " milliseconds.");

				// Quick sort Original
				for (int i = 0; i < size; i++)
					arr[i] = arrCopy[i];
				start = finish;
				if (size < 101)
					printArray("in");
				quicksort(0, size - 1);
				if (size < 101)
					printArray("out");
				finish = System.currentTimeMillis();
				System.out.println("quicksortOriginal: " + (finish - start) + " milliseconds.");
				
				// Quick sorta
				for (int i = 0; i < size; i++)
					arr[i] = arrCopy[i];
				start = finish;
				if (size < 101)
					printArray("in");
				quicksorta(0, size - 1);
				if (size < 101)
					printArray("out");
				finish = System.currentTimeMillis();
				System.out.println("quicksortA: " + (finish - start) + " milliseconds.");
				
				// Quick sort B
				for (int i = 0; i < size; i++)
					arr[i] = arrCopy[i];
				start = finish;
				if (size < 101)
					printArray("in");
				quicksortb(0, size - 1);
				if (size < 101)
					printArray("out");
				finish = System.currentTimeMillis();
				System.out.println("quicksortB: " + (finish - start) + " milliseconds.");
				
				// BottumUp Merge Sort
				for (int i = 0; i < size; i++)
					arr[i] = arrCopy[i];
				start = finish;
				if (size < 101)
					printArray("in");
				bottumUpMergeSort();
				if (size < 101)
					printArray("out");
				finish = System.currentTimeMillis();
				System.out.println("bottumUpMergeSort: " + (finish - start) + " milliseconds.");
	
				// arr[0..size-1] is already sorted. We randomly swap 100 pairs to
				// make it nearly-sorted.
				for (int i = 0; i < 100; i++) {
					int j = randomGenerator.nextInt(size);
					int k = randomGenerator.nextInt(size);
					exchange(j, k);
				}
				for (int i = 0; i < size; i++)
					arrCopy2[i] = arr[i];

				// Quick sort on nearly-sorted array
				start = finish;
				if (size < 101)
					printArray("in");
				quicksort(0, size - 1);
				if (size < 101)
					printArray("out");
				finish = System.currentTimeMillis();
				System.out.println("quicksort on nearly-sorted: " + (finish - start) + " milliseconds.");

				// Insert sort on nearly-sorted array
				for (int i = 0; i < size; i++)
					arr[i] = arrCopy2[i];
				start = finish;
				if (size < 101)
					printArray("in");
				insertionSort();
				if (size < 101)
					printArray("out");
				finish = System.currentTimeMillis();
				System.out.println("insertsort on nearly-sorted: " + (finish - start) + " milliseconds.");
			}


		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
