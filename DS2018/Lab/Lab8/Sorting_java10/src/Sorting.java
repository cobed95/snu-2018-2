import java.lang.Math;
import java.util.Arrays;

public class Sorting {  
	
    public static void selectionSort(int[] arr){
    	// TODO
		// Implement selection sort in decreasing order
        for (int i = 0; i < arr.length - 1; i++) {
            int maxIdx = findMax(arr, i);
            swap(arr, i, maxIdx);
        }
    }
    
    public static void shellSort(int arr[]) {
    	// TODO
		// Implement shell sort in decreasing order
        for (int i = arr.length / 2; i >= 2; i /= 2) {
            for (int j = 0; j < i; j++) {
                insertionSort(arr, j, i);
            }
        }
        insertionSort(arr, 0, 1);
    	
    }

    public static void insertionSort(int arr[], int start, int incr) {
        for (int i = start + incr; i < arr.length; i += incr) {
            int j = i - incr;
            int key = arr[i];
            while (j >= start && arr[j] < key) {
                arr[j + incr] = arr[j];
                j -= incr;
            }
            arr[j + incr] = key;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        assert i < arr.length && j < arr.length : "ArrayIndex out of range.";
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int findMax(int[] arr, int from) {
        int maxIdx = from;
        int max = arr[from];
        for (int i = from + 1; i < arr.length; i++) {
            if (arr[i] > max) {
                maxIdx = i;
                max = arr[i];
            }
        }
        return maxIdx;
    }

    public static int[] copyArray(int[] arr) {
    	
    	int len = arr.length;
    	int[] arr_cp = new int[len];
		System.arraycopy(arr, 0, arr_cp, 0, len);
    	return arr_cp;
    }
    
    public static void printArray(int[] arr) {
    	for (int i = 0; i<arr.length; i++)
    		System.out.print(arr[i] + " ");
    	System.out.println();
    	System.out.println();
    }
    
    
    public static void initArray(int[] arr, int len) {
    	int randomNumber = 0 , i;
    	for (i = 0; i < len; i++) {
        	randomNumber = (int)Math.round(Math.random()*100);
	    	arr[i] = randomNumber;
    	}
    }
    
        
    public static void main(String[] args) {  
        
    	int len = 61;     //you can change the length of the array
    	int[] arr = new int[len];
    	initArray(arr, len);
    	int[] arr1 = copyArray(arr);
    	int[] arr2 = copyArray(arr);
    	
    	System.out.println("Before Sort:");
        printArray(arr);
       	
        selectionSort(arr1); 
        System.out.println("After Selection Sort:");  
    	printArray(arr1);
        
        shellSort(arr2);
        System.out.println("After Shell Sort:");
    	printArray(arr2);
        
    	if (Arrays.equals(arr1, arr2))
    		System.out.println("Perfect!");
    	else
    		System.out.println("Try Again");

    }  
}  

