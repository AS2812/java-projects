package javaapplication122;

import java.util.Arrays;
import java.util.Random;
public class JavaApplication122 {

    public static void main(String[] args) {


        // Test the array generators
        int[] sortedArray = generateSortedArray();
        int[] reverseSortedArray = generateReverseSortedArray();
        int[] randomArray = generateRandomArray();

        // Timing variables
        long startTime, endTime;
        
        // Bubble Sort
        System.out.println("Bubble Sort:");
        startTime = System.nanoTime();
        bubblesort bub1 = bubblesort.sort(Arrays.copyOf(sortedArray, sortedArray.length));
        endTime = System.nanoTime();
        printTime("Sorted Array", bub1, endTime - startTime);

        startTime = System.nanoTime();
        bubblesort bub2 = bubblesort.sort(Arrays.copyOf(randomArray, randomArray.length));
        endTime = System.nanoTime();
        printTime("Random Array", bub2, endTime - startTime);

        startTime = System.nanoTime();
        bubblesort bub3 = bubblesort.sort(Arrays.copyOf(reverseSortedArray, reverseSortedArray.length));
        endTime = System.nanoTime();
        printTime("Reverse Sorted Array", bub3, endTime - startTime);

        // Counting Sort
        System.out.println("\nCounting Sort:");
        startTime = System.nanoTime();
        CountSortResult coty1 = countsort.sort(Arrays.copyOf(sortedArray, sortedArray.length));
        endTime = System.nanoTime();
        printTime("Sorted Array", coty1, endTime - startTime);

        startTime = System.nanoTime();
        CountSortResult coty2 = countsort.sort(Arrays.copyOf(randomArray, randomArray.length));
        endTime = System.nanoTime();
        printTime("Random Array", coty2, endTime - startTime);

        startTime = System.nanoTime();
        CountSortResult coty3 = countsort.sort(Arrays.copyOf(reverseSortedArray, reverseSortedArray.length));
        endTime = System.nanoTime();
        printTime("Reverse Sorted Array", coty3, endTime - startTime);

        // Quick Sort
        System.out.println("\nQuick Sort:");
        startTime = System.nanoTime();
        quicksort qsort1 = new quicksort(Arrays.copyOf(sortedArray, sortedArray.length));
        int[] sortedResult = qsort1.quicksort(0, sortedArray.length - 1);
        endTime = System.nanoTime();
        printTime("Sorted Array", sortedResult, endTime - startTime);

        startTime = System.nanoTime();
        quicksort qsort2 = new quicksort(Arrays.copyOf(randomArray, randomArray.length));
        int[] randomResult = qsort2.quicksort(0, randomArray.length - 1);
        endTime = System.nanoTime();
        printTime("Random Array", randomResult, endTime - startTime);

        startTime = System.nanoTime();
        quicksort qsort3 = new quicksort(Arrays.copyOf(reverseSortedArray, reverseSortedArray.length));
        int[] reverseSortedResult = qsort3.quicksort(0, reverseSortedArray.length - 1);
        endTime =System.nanoTime();

        printTime("Reverse Sorted Array", reverseSortedResult, endTime - startTime);
    }

    // Array generation methods (unchanged)
    static int[] generateSortedArray() {
        int[] array = new int[10000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        return array;
    }

    static int[] generateReverseSortedArray() {
        int[] array = new int[10000];
        for (int i = 0; i < array.length; i++) {
            array[i] = array.length - i - 1;
        }
        return array;
    }

    static int[] generateRandomArray() {
        Random random = new Random();
        int[] array = new int[10000];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(10000); // Assuming maximum value is 10000
        }
        return array;
    }

    // Method to print time and other relevant information
  static void printTime(String arrayType, bubblesort result, long time) {
    System.out.printf("%-20s | Comparisons: %-10d | Interchanges: %-10d | Time: %-10d nanoseconds%n",
            arrayType, result.comparisons, result.interchanges, time);
}

static void printTime(String arrayType, CountSortResult result, long time) {
    System.out.printf("%-20s | Comparisons: %-10d | Interchanges: %-10d | Time: %-10d nanoseconds%n",
            arrayType, result.comparisons, result.interchanges, time);
}

static void printTime(String arrayType, int[] result, long time) {
    System.out.printf("%-20s | Comparisons: %-10d | Interchanges: %-10d | Time: %-10d nanoseconds%n",
            arrayType, result[0], result[1], time);
}
}
 class bubblesort {
    int[] sortedArray;
    int comparisons;
    int interchanges;

    public bubblesort(int[] sortedArray, int comparisons, int interchanges) {
        this.sortedArray = sortedArray;
        this.comparisons = comparisons;
        this.interchanges = interchanges;
    }

    static bubblesort sort(int[] arr) {
        int n = arr.length;
        int comparisons = 0;
        int interchanges = 0;
        boolean sorted = true; // Assume the array is sorted initially

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false; // Flag to track if any swap occurs in this pass
            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;
                if (arr[j] > arr[j + 1]) {
                    interchanges++;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true; // Mark that a swap occurred
                }
            }
            // If no swap occurred in this pass, the array is already sorted
            if (!swapped) {
                sorted = true;
                break;
            }
        }

        // If the array was already sorted, return without creating a new instance
        if (sorted) {
            return new bubblesort(arr, comparisons, interchanges);
        } else {
            // If the array was not already sorted, proceed with normal bubble sort
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    comparisons++;
                    if (arr[j] > arr[j + 1]) {
                        interchanges++;
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
            return new bubblesort(arr, comparisons, interchanges);
        }
    }
}
class CountSortResult {
    int[] sorted_array;
    int comparisons;
    int interchanges;

    public CountSortResult(int[] sorted_array, int comparisons, int interchanges) {
        this.sorted_array = sorted_array;
        this.comparisons = comparisons;
        this.interchanges = interchanges;
    }
}

class countsort {
    public static CountSortResult sort(int[] arr) {
        if (arr == null || arr.length == 0)
            return null;

        // Find the maximum value in the array
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }

        // Create a count array to store the count of each element
        int[] count = new int[max + 1];

        // Store the count of each element in the input array
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }

        // Build the sorted output array
        int[] output = new int[arr.length];
        int index = 0;
        for (int i = 0; i <= max; i++) {
            while (count[i] > 0) {
                output[index++] = i;
                count[i]--;
            }
        }

        // Calculate the number of comparisons and interchanges
        int comparisons = 0;
        int interchanges = 0;
        for (int i = 1; i <= max; i++) {
            if (count[i] > 0) {
                comparisons += arr.length - count[i - 1];
                interchanges += count[i];
            }
        }

        return new CountSortResult(output, comparisons, interchanges);
    }
}
 class quicksort {
    int comp;
    int interchange;
    int[] array;

    public quicksort(int[] array) {
        this.array = array;
        this.comp = 0;
        this.interchange = 0;
    }

    public int[] quicksort(int low, int high) {
        if (low < high) {
            int pivot = partition(low, high);
            quicksort(low, pivot - 1);
            quicksort(pivot + 1, high);
        }
        return new int[]{comp, interchange};
    }

    int partition(int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j <= high - 1; j++) {
            comp++;
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                interchange++;
            }
        }
        i++;
        int temp = array[i];
        array[i] = array[high];
        array[high] = temp;
        interchange++;
        return i;
    }
}
