
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;

import java.util.Arrays;

/**
 *
 * @author ahmed
 */
public class Main {


    public static void main(String[] args) {
         int[] randomArray = generateRandomArray(10000);
        int[] sortedArray = generateSortedArray(10000);
        int[] inverselySortedArray = generateInverselySortedArray(10000);

        System.out.println("Performance comparison for Random Array :");
        compareSortingTechniques(randomArray);

        System.out.println("\nPerformance comparison for Sorted Array :");
        compareSortingTechniques(sortedArray);

        System.out.println("\nPerformance comparison for Inversely Sorted Array :");
        compareSortingTechniques(inverselySortedArray);

        // Generate and display the graph
        displayPerformanceGraph(randomArray, sortedArray, inverselySortedArray);
    }

    public static void displayPerformanceGraph(int[] randomArray, int[] sortedArray, int[] inverselySortedArray) {
    // Create Chart
    CategoryChart chart = new CategoryChartBuilder()
            .width(800)
            .height(600)
            .title("Sorting Technique Performance Comparison")
            .xAxisTitle("Sorting Technique")
            .yAxisTitle("Runtime (ns)")
            .theme(Styler.ChartTheme.GGPlot2)
            .build();

    // Customize Chart
    chart.getStyler().setLegendVisible(true);

    // Add series
    chart.addSeries("Bubble Sort", Arrays.asList("Random Array", "Sorted Array", "Inversely Sorted Array"),
            Arrays.asList(getBubbleSortRuntime(randomArray), getBubbleSortRuntime(sortedArray), getBubbleSortRuntime(inverselySortedArray)));
    chart.addSeries("Linear Sort (Counting Sort)", Arrays.asList("Random Array", "Sorted Array", "Inversely Sorted Array"),
            Arrays.asList(getCountingSortRuntime(randomArray), getCountingSortRuntime(sortedArray), getCountingSortRuntime(inverselySortedArray)));
    chart.addSeries("Quick Sort", Arrays.asList("Random Array", "Sorted Array", "Inversely Sorted Array"),
            Arrays.asList(getQuickSortRuntime(randomArray), getQuickSortRuntime(sortedArray), getQuickSortRuntime(inverselySortedArray)));

    // Display chart
    new SwingWrapper<>(chart).displayChart();
}

 // Helper methods to get runtime for each sorting technique
    public static long getBubbleSortRuntime(int[] array) {
        long startTime = System.nanoTime();
        bubbleSort(Arrays.copyOf(array, array.length));
        return System.nanoTime() - startTime;
    }



    public static long getQuickSortRuntime(int[] array) {
        long startTime = System.nanoTime();
        quickSort(Arrays.copyOf(array, array.length), 0, array.length - 1);
        return System.nanoTime() - startTime;
    }

public static long getCountingSortRuntime(int[] array) {
    long startTime = System.nanoTime();
    countingSort(Arrays.copyOf(array, array.length));
    return System.nanoTime() - startTime;
}



    public static int [] bubbleSort(int[] array) {
    int n = array.length;
    bubbleComparisons = 0;
    bubbleInterchanges = 0;
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            bubbleComparisons++; // Increment comparison count
            if (array[j] > array[j + 1]) {
                int temp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = temp;
                bubbleInterchanges++; // Increment interchange count
            }
        }
    if(bubbleInterchanges==0)
        return array;


    }
    return array;
}


  public static void countingSort(int[] array) {
    int max = getMax(array);
    int[] count = new int[max + 1];

    // Count occurrences of each element
    for (int num : array) {
        count[num]++;
    }

    int k = 0;

    // Iterate through the count array to find the positions of the elements
    for (int i = 0; i < count.length; i++) {
        if (count[i] > 0) {
            int index = linearSearch(i, array); // Find the position of the element i in the original array
            int occurrences = count[i];

            // Add the element i 'occurrences' times in the sorted array
            for (int j = 0; j < occurrences; j++) {
                array[k++] = i;
            }
        }
    }
}

public static int linearSearch(int target, int[] a) {
    for (int i = 0; i < a.length; i++) {
        if (target == a[i]) return i;
    }
    return -1; // not a legal index
}

    static int quickComparisons;
    static int quickInterchanges;

    public static void quickSort(int[] array, int low, int high) {
    if (low < high) {
        int pi = partition(array, low, high);
        quickSort(array, low, pi - 1);
        quickSort(array, pi + 1, high);
    }
}

private static int partition(int[] A, int low, int high) {
    int pivotIndex = low; // Assume first element is the pivot
    int pivot = A[low]; // The pivot value
    A[pivotIndex] = A[high]; // Swap pivot with last item
    A[high] = pivot;
    int i = low - 1;
    int j = high;
    do {
        do {
            i++;
        } while (A[i] < pivot && i < high); // Added i < high condition to prevent ArrayIndexOutOfBoundsException
        do {
            j--;
        } while (A[j] > pivot && j > low); // Added j > low condition to prevent ArrayIndexOutOfBoundsException
        if (i < j) {
            int Temp = A[i];
            A[i] = A[j];
            A[j] = Temp;
        }
    } while (i < j);
    A[high] = A[i]; // Put the pivot back in the middle
    A[i] = pivot;
    return i;
}

    static int bubbleComparisons;
    static int bubbleInterchanges;

    public static int getMax(int[] arr) {
        if (arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty");
        }

        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 10);
        }
        return array;
    }

    public static int[] generateSortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array;
    }

    public static int[] generateInverselySortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - i;
        }
        return array;
    }

    public static String getRuntime(int[] array, String sortType) {
        long startTime, endTime;
        int[] copy = Arrays.copyOf(array, array.length);
        startTime = System.nanoTime();
        switch (sortType) {
            case "bubble":
                bubbleSort(copy);
                break;
            case "counting":
                countingSort(copy);
                break;
            case "quick":
                quickSort(copy, 0, copy.length - 1);
                break;
            default:
                return "Invalid Sort Type";
        }
        endTime = System.nanoTime();
        return String.valueOf(endTime - startTime) + "ns";
    }

public static void compareSortingTechniques(int[] array) {
    long startTime, endTime;
    int[] copy;

    // Bubble Sort
    copy = Arrays.copyOf(array, array.length);
    startTime = System.nanoTime();
    bubbleSort(copy);
    endTime = System.nanoTime();
    System.out.println("Bubble Sort: Runtime=" + (endTime - startTime) + "ns, Comparisons=" + bubbleComparisons + ", Interchanges=" + bubbleInterchanges);

    // Linear Sort (Counting Sort)
    copy = Arrays.copyOf(array, array.length);
    startTime = System.nanoTime();
    countingSort(copy);
    endTime = System.nanoTime();
    System.out.println("Linear Sort (Counting Sort): Runtime=" + (endTime - startTime) + "ns");

    // Quick Sort
    copy = Arrays.copyOf(array, array.length);
    startTime = System.nanoTime();
    quickSort(copy, 0, copy.length - 1);
    endTime = System.nanoTime();
    System.out.println("Quick Sort: Runtime=" + (endTime - startTime) + "ns, Comparisons=" + quickComparisons + ", Interchanges=" + quickInterchanges);
}
}
