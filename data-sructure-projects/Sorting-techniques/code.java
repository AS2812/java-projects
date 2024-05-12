
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

public static long getCountingSortRuntime(int[] array) {
    long startTime = System.nanoTime();
    countingSort(Arrays.copyOf(array, array.length));
    return System.nanoTime() - startTime;
}


    public static void bubbleSort(int[] array) {
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
        }
    }

    public static void countingSort(int[] array) {
        int max = getMax(array);
        int[] count = new int[max + 1];
        for (int num : array) {
            count[num]++;
        }
        int k = 0;
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[i]; j++) {
                array[k++] = i;
            }
        }
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

    public static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            quickComparisons++; // Increment comparison count
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                quickInterchanges++; // Increment interchange count
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        quickInterchanges++; // Increment interchange count
        return i + 1;
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
            array[i] = (int) (Math.random() * size);
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
