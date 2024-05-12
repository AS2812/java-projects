import java.util.Arrays;
  class Main {

    public static void main(String[] args)
    {
        int[] randomArray = generateRandomArray(10);
        int[] sortedArray = generateSortedArray(10);
        int[] inverselySortedArray = sortedArray;
        int[] array_size_10000 = new int[10000];
        for(int i = 0 ; i<array_size_10000.length ; i++)
        {
            array_size_10000[i] = i;
        }

        System.out.println("Performance comparison for Random Array :");
        compareSortingTechniques(randomArray);

        System.out.println("\nPerformance comparison for Sorted Array :");
        compareSortingTechniques(sortedArray);

        System.out.println("\nPerformance comparison for Inversely Sorted Array :");
        compareSortingTechniques(inverselySortedArray);

         System.out.println("\nPerformance comparison for size of arrays  (size 10,000):");
         compareSortingTechniques(array_size_10000);
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
        System.out.println("Bubble Sort: Runtime=" + (endTime - startTime) + "ns, Comparisons=" + bubbleComparisons + ", Interchanges=" + bubbleInterchanges);

        // Linear Sort (Counting Sort)
        copy = Arrays.copyOf(array, array.length);
        startTime = System.nanoTime();
        countingSort(copy);
        endTime = System.nanoTime();
        System.out.println("Linear Sort: Runtime=" + (endTime - startTime) + "ns");

        // Quick Sort
        copy = Arrays.copyOf(array, array.length);
        startTime = System.nanoTime();
        quickSort(copy, 0, copy.length - 1);
        endTime = System.nanoTime();
        System.out.println("Quick Sort: Runtime=" + (endTime - startTime) + "ns, Comparisons=" + quickComparisons + ", Interchanges=" + quickInterchanges);
    }

    public static void bubbleSort(int[] array)
    {
        bubbleComparisons = 0;
        bubbleInterchanges = 0;
        int n = array.length;
        for (int i = 0; i < n - 1; i++)
        {
            for (int j = 0; j < n - i - 1; j++)
            {
                bubbleComparisons++;
                if (array[j] > array[j + 1])
                {
                    // swap temp and arr[i]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    bubbleInterchanges++;
                }
            }
        }
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
            for (int j = 0; j < count[i]; j++)
            {
                array[k++] = i;
            }
        }
    }

    static int quickComparisons;
    static int quickInterchanges;

    public static void quickSort(int[] array, int low, int high)
    {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    public static int partition(int[] array, int low, int high)
    {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            quickComparisons++;
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                quickInterchanges++;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        quickInterchanges++;
        return i + 1;
    }

    static int bubbleComparisons;
    static int bubbleInterchanges;

    public static int getMax(int[] arr)
    {
        if (arr.length == 0)
        {
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

    public static int[] generateRandomArray(int size)
    {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * size);
        }
        return array;
    }

    public static int[] generateSortedArray(int size)
    {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array;
    }

    public static int[] generateInverselySortedArray(int size)
    {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - i;
        }
        return array;
}
}
