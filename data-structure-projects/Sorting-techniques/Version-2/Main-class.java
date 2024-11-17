import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;
import processing.core.PApplet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;

public class Main {

    private static int countQuickComparisons = 0;
    private static int countQuickInterchanges = 0;
    private static int countBubbleComparisons = 0;
    private static int countBubbleInterchanges = 0;

    public static void main(String[] args) {
        int[] randomArray = generateRandomArray(10000);
        int[] sortedArray = generateSortedArray(10000);
        int[] inverselySortedArray = generateInverselySortedArray(100);

        System.out.println("Performance comparison for Random Array :");
        compareSortingTechniques(randomArray);

        System.out.println("\nPerformance comparison for Sorted Array :");
        compareSortingTechniques(sortedArray);

        System.out.println("\nPerformance comparison for Inversely Sorted Array :");
        compareSortingTechniques(inverselySortedArray);

        // Create and display the visualization
        BubbleSortVisualization bubbleSortVisualizer = new BubbleSortVisualization(inverselySortedArray);
        PApplet.runSketch(new String[]{"BubbleSortVisualizer"}, bubbleSortVisualizer);


        // Generate and display the graph
       // displayPerformanceGraph(randomArray, sortedArray, inverselySortedArray);

        // Display the performance comparison table
        //displayPerformanceTable(randomArray, sortedArray, inverselySortedArray);

        // Create and display the visualization
        QuickSortVisualization visualization = new QuickSortVisualization(inverselySortedArray,800);
        JFrame frame = new JFrame("Quick Sort Visualization");
        JScrollPane scrollPane = new JScrollPane(visualization);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        frame.getContentPane().add(scrollPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void displayPerformanceTable(int[] randomArray, int[] sortedArray, int[] inverselySortedArray)
    {
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        model.addColumn("Array Type");
        model.addColumn("Sorting Technique");
        model.addColumn("Runtime (ns)");
        model.addColumn("Comparisons");
        model.addColumn("Interchanges");

        addRow(model, "Random Array", "Bubble Sort", getBubbleSortRuntime(randomArray), countBubbleComparisons, countBubbleInterchanges);
        addRow(model, "Random Array", "Linear Sort ", getCountingSortRuntime(randomArray), 0, 0);
        addRow(model, "Random Array", "Quick Sort", getQuickSortRuntime(randomArray), countQuickComparisons, countQuickInterchanges);

        addRow(model, "Sorted Array", "Bubble Sort", getBubbleSortRuntime(sortedArray), countBubbleComparisons, countBubbleInterchanges);
        addRow(model, "Sorted Array", "Linear Sort ", getCountingSortRuntime(sortedArray), 0, 0);
        addRow(model, "Sorted Array", "Quick Sort", getQuickSortRuntime(sortedArray), countQuickComparisons, countQuickInterchanges);

        addRow(model, "Inversely Sorted Array", "Bubble Sort", getBubbleSortRuntime(inverselySortedArray), countBubbleComparisons, countBubbleInterchanges);
        addRow(model, "Inversely Sorted Array", "Linear Sort ", getCountingSortRuntime(inverselySortedArray), 0, 0);
        addRow(model, "Inversely Sorted Array", "Quick Sort", getQuickSortRuntime(inverselySortedArray), countQuickComparisons, countQuickInterchanges);

        JScrollPane scrollPane = new JScrollPane(table);

        JFrame frame = new JFrame("Performance Comparison Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void addRow(DefaultTableModel model, String arrayType, String sortingTechnique, long runtime, int comparisons, int interchanges)
    {
        model.addRow(new Object[]{arrayType, sortingTechnique, runtime, comparisons, interchanges});
    }

    public static void displayPerformanceGraph(int[] randomArray, int[] sortedArray, int[] inverselySortedArray)
    {
        CategoryChart chart = new CategoryChartBuilder()
                .width(800)
                .height(600)
                .title("Sorting Technique Performance Comparison")
                .xAxisTitle("Sorting Technique")
                .yAxisTitle("Runtime (ns)")
                .theme(Styler.ChartTheme.GGPlot2)
                .build();

        chart.getStyler().setLegendVisible(true);

        chart.addSeries("Bubble Sort", Arrays.asList("Random Array", "Sorted Array", "Inversely Sorted Array"),
                Arrays.asList(getBubbleSortRuntime(randomArray), getBubbleSortRuntime(sortedArray), getBubbleSortRuntime(inverselySortedArray)));
        chart.addSeries("Linear Sort (Counting Sort)", Arrays.asList("Random Array", "Sorted Array", "Inversely Sorted Array"),
                Arrays.asList(getCountingSortRuntime(randomArray), getCountingSortRuntime(sortedArray), getCountingSortRuntime(inverselySortedArray)));
        chart.addSeries("Quick Sort", Arrays.asList("Random Array", "Sorted Array", "Inversely Sorted Array"),
                Arrays.asList(getQuickSortRuntime(randomArray), getQuickSortRuntime(sortedArray), getQuickSortRuntime(inverselySortedArray)));

        new SwingWrapper<>(chart).displayChart();
    }

    public static long getBubbleSortRuntime(int[] array)
    {
        long startTime = System.nanoTime();
        bubbleSort(Arrays.copyOf(array, array.length));
        return System.nanoTime() - startTime;
    }

    public static long getQuickSortRuntime(int[] array)
    {
        long startTime = System.nanoTime();
        quickSort(Arrays.copyOf(array, array.length), 0, array.length - 1);
        return System.nanoTime() - startTime;
    }

    public static long getCountingSortRuntime(int[] array)
    {
        long startTime = System.nanoTime();
        countingSort(Arrays.copyOf(array, array.length));
        return System.nanoTime() - startTime;
    }

    public static int[] bubbleSort(int[] array)
    {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++)
        {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++)
            {
                countBubbleComparisons++;
                if (array[j] > array[j + 1])
                {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                    countBubbleInterchanges++;
                }
            }
            if (!swapped)
            {
                break;
            }
        }
        return array;
    }

    public static void countingSort(int[] array)
    {
        int max = getMax(array);
        int[] count = new int[max + 1];
        for (int num : array)
        {
            count[num]++;
        }
        int k = 0;
        for (int i = 0; i < count.length; i++)
        {
            if (count[i] > 0)
            {
                int index = linearSearch(i, array);
                int occurrences = count[i];
                for (int j = 0; j < occurrences; j++)
                {
                    array[k++] = i;
                }
            }
        }
    }

    public static int linearSearch(int target, int[] a)
    {
        for (int i = 0; i < a.length; i++)
        {
            if (target == a[i]) return i;
        }
        return -1;
    }

   public static void quickSort(int[] arr, int start, int end)
   {
        if (start >= end)
        {
            return;
        }
        int index = partition(arr, start, end);
        quickSort(arr, start, index - 1);
        quickSort(arr, index + 1, end);
    }

    public static int partition(int[] array, int low, int high)
    {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++)
        {
            countQuickComparisons++;
            if (array[j] < pivot)
            {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                countQuickInterchanges++;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    public static int getMax(int[] arr)
    {
        if (arr.length == 0)
        {
            throw new IllegalArgumentException("Array cannot be empty");
        }
        int max = arr[0];
        for (int num : arr)
        {
            if (num > max)
            {
                max = num;
            }
        }
        return max;
    }

    public static int[] generateRandomArray(int size)
    {
        int[] array = new int[size];
        for (int i = 0; i < size; i++)
        {
            array[i] = (int) (Math.random() * 10);
        }
        return array;
    }

    public static int[] generateSortedArray(int size)
    {
        int[] array = new int[size];
        for (int i = 0; i < size; i++)
        {
            array[i] = i;
        }
        return array;
    }

    public static int[] generateInverselySortedArray(int size)
    {
        int[] array = new int[size];
        for (int i = 0; i < size; i++)
        {
            array[i] = size - i;
        }
        return array;
    }

 public static void compareSortingTechniques(int[] array)
 {
    long startTime, endTime;
    int[] copy;

    // Bubble Sort
    copy = Arrays.copyOf(array, array.length);
    startTime = System.nanoTime();
    bubbleSort(copy);
    endTime = System.nanoTime();
    long bubbleRuntime = endTime - startTime;
    int bubbleComparisons = countBubbleComparisons;
    int bubbleInterchanges = countBubbleInterchanges;
    System.out.println("Bubble Sort: Runtime=" + bubbleRuntime + "ns, Comparisons=" + bubbleComparisons + ", Interchanges=" + bubbleInterchanges);

    // Linear Sort (Counting Sort)
    copy = Arrays.copyOf(array, array.length);
    startTime = System.nanoTime();
    countingSort(copy);
    endTime = System.nanoTime();
    long countingRuntime = endTime - startTime;
    System.out.println("Linear Sort (Counting Sort): Runtime=" + countingRuntime + "ns");

    // Quick Sort
    copy = Arrays.copyOf(array, array.length);
    startTime = System.nanoTime();
    quickSort(copy, 0, copy.length - 1);
    endTime = System.nanoTime();
    long quickRuntime = endTime - startTime;
    int quickComparisons = countQuickComparisons;
    int quickInterchanges = countQuickInterchanges;
    System.out.println("Quick Sort: Runtime=" + quickRuntime + "ns, Comparisons=" + quickComparisons + ", Interchanges=" + quickInterchanges);

    // Determine and print the order of sorting
    if (bubbleRuntime < countingRuntime && countingRuntime < quickRuntime)
    {
        System.out.println("Faster : Bubble Sort > Linear Sort > Quick Sort");
    }
    else if (bubbleRuntime < quickRuntime && quickRuntime < countingRuntime)
    {
        System.out.println("Faster : Bubble Sort > Quick Sort > Linear Sort");
    }
    else if (countingRuntime < bubbleRuntime && bubbleRuntime < quickRuntime)
    {
        System.out.println("Faster : Linear Sort > Bubble Sort > Quick Sort");
    }
    else if (countingRuntime < quickRuntime && quickRuntime < bubbleRuntime)
    {
        System.out.println("Faster : Linear Sort > Quick Sort > Bubble Sort");
    }
    else if (quickRuntime < bubbleRuntime && bubbleRuntime < countingRuntime)
    {
        System.out.println("Faster : Quick Sort > Bubble Sort > Linear Sort");
    }
    else if (quickRuntime < countingRuntime && countingRuntime < bubbleRuntime)
    {
        System.out.println("Faster: Quick Sort > Linear Sort > Bubble Sort");
    }
}


}
