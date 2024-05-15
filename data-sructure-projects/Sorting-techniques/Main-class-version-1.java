import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int[] sortedArray = generateSortedArray();
        int[] reverseSortedArray = generateReverseSortedArray();
        int[] randomArray = generateRandomArray();

        Result[] results = new Result[9];
        long startTime, endTime;

        // Bubble Sort
        startTime = System.nanoTime();
        bubblesort bub1 = bubblesort.sort(Arrays.copyOf(sortedArray, sortedArray.length));
        endTime = System.nanoTime();
        results[0] = new Result("Bubble Sort", "Sorted Array", bub1.comparisons, bub1.interchanges, endTime - startTime);

        startTime = System.nanoTime();
        bubblesort bub2 = bubblesort.sort(Arrays.copyOf(randomArray, randomArray.length));
        endTime = System.nanoTime();
        results[1] = new Result("Bubble Sort", "Random Array", bub2.comparisons, bub2.interchanges, endTime - startTime);

        startTime = System.nanoTime();
        bubblesort bub3 = bubblesort.sort(Arrays.copyOf(reverseSortedArray, reverseSortedArray.length));
        endTime = System.nanoTime();
        results[2] = new Result("Bubble Sort", "Reverse Sorted Array", bub3.comparisons, bub3.interchanges, endTime - startTime);

        // Counting Sort
        startTime = System.nanoTime();
        countsort coty1 = countsort.sort(Arrays.copyOf(sortedArray, sortedArray.length));
        endTime = System.nanoTime();
        results[3] = new Result("Counting Sort", "Sorted Array", -1, -1, endTime - startTime);

        startTime = System.nanoTime();
        countsort coty2 = countsort.sort(Arrays.copyOf(randomArray, randomArray.length));
        endTime = System.nanoTime();
        results[4] = new Result("Counting Sort", "Random Array", -1, -1, endTime - startTime);

        startTime = System.nanoTime();
        countsort coty3 = countsort.sort(Arrays.copyOf(reverseSortedArray, reverseSortedArray.length));
        endTime = System.nanoTime();
        results[5] = new Result("Counting Sort", "Reverse Sorted Array", -1, -1, endTime - startTime);

        // Quick Sort
        startTime = System.nanoTime();
        quicksort qsort1 = new quicksort(Arrays.copyOf(sortedArray, sortedArray.length));
        int[] sortedResult = qsort1.quicksort(0, sortedArray.length - 1);
        endTime = System.nanoTime();
        results[6] = new Result("Quick Sort", "Sorted Array", sortedResult[0], sortedResult[1], endTime - startTime);

        startTime = System.nanoTime();
        quicksort qsort2 = new quicksort(Arrays.copyOf(randomArray, randomArray.length));
        int[] randomResult = qsort2.quicksort(0, randomArray.length - 1);
        endTime = System.nanoTime();
        results[7] = new Result("Quick Sort", "Random Array", randomResult[0], randomResult[1], endTime - startTime);

        startTime = System.nanoTime();
        quicksort qsort3 = new quicksort(Arrays.copyOf(reverseSortedArray, reverseSortedArray.length));
        int[] reverseSortedResult = qsort3.quicksort(0, reverseSortedArray.length - 1);
        endTime = System.nanoTime();
        results[8] = new Result("Quick Sort", "Reverse Sorted Array", reverseSortedResult[0], reverseSortedResult[1], endTime - startTime);

        SwingUtilities.invokeLater(() -> createAndShowGUI(results));
    }

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
            array[i] = random.nextInt(10000);
        }
        return array;
    }

    static void createAndShowGUI(Result[] results) {
        JFrame frame = new JFrame("Sorting Algorithms Comparison");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);

        String[] columnNames = {"Sort Type", "Array Type", "Comparisons", "Interchanges/Swaps", "Time (nanoseconds)"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);

        for (Result result : results) {
            String comparisons = result.comparisons == -1 ? "N/A" : String.valueOf(result.comparisons);
            String interchanges = result.interchanges == -1 ? "N/A" : String.valueOf(result.interchanges);
            model.addRow(new Object[]{result.sortType, result.arrayType, comparisons, interchanges, result.time});
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Result result : results) {
            dataset.addValue(result.time, result.sortType + " - " + result.arrayType, "Time");
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Sorting Algorithms Comparison",
                "Algorithm and Array Type",
                "Time (nanoseconds)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(1100, 600));

        JButton startButton = new JButton("Start Sorting Visualization");
        int[] randomArray = GUIsimulation.generateRandomArray(10000); // Full size for visualization
        startButton.addActionListener(e -> openVisualizationWindow(randomArray));

        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(table), BorderLayout.NORTH);
        frame.add(chartPanel, BorderLayout.CENTER);
        frame.add(startButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    static void openVisualizationWindow(int[] array) {
        JFrame visualizationFrame = new JFrame("Sorting Visualization");
        visualizationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        visualizationFrame.setSize(800, 600);

        GUIsimulation guiSimulation = new GUIsimulation(array);
        guiSimulation.startSorting();

        visualizationFrame.add(guiSimulation);
        visualizationFrame.setVisible(true);
    }

    static class Result {
        String sortType;
        String arrayType;
        int comparisons;
        int interchanges;
        long time;

        Result(String sortType, String arrayType, int comparisons, int interchanges, long time) {
            this.sortType = sortType;
            this.arrayType = arrayType;
            this.comparisons = comparisons;
            this.interchanges = interchanges;
            this.time = time;
        }
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

class countsort {
    int[] sorted_array;
    static int key_assignments;

    public countsort(int[] sorted_array) {
        this.sorted_array = sorted_array;
    }

    public static countsort sort(int[] arr) {
        if (arr == null || arr.length == 0)
            return null;

        // Reset key_assignments for each sort operation
        key_assignments = 0;

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
                key_assignments++; // Count key assignments
            }
        }
        return new countsort(output);
    }
}

class quicksort {
    int comp;
    int swaps;
    int[] array;

    public quicksort(int[] array) {
        this.array = array;
        this.comp = 0;
        this.swaps = 0;
    }

    public int[] quicksort(int low, int high) {
        if (low < high) {
            int pivot = partition(low, high);
            quicksort(low, pivot - 1);
            quicksort(pivot + 1, high);
        }
        return new int[]{comp, swaps};
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
                swaps++;
            }
        }
        i++;
        int temp = array[i];
        array[i] = array[high];
        array[high] = temp;
        swaps++;
        return i;
    }
}
