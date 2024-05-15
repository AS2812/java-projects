import javax.swing.*;
import java.awt.*;

public class QuickSortVisualization extends JPanel implements Runnable {
    private static final long serialVersionUID = 1L;
    private int[] values;
    private int[] states;

    // Adjust these values for better visualization on small screens
    private final int MIN_BAR_WIDTH = 2; // Minimum allowed bar width
    private final int MIN_GAP = 1; // Minimum allowed gap between bars

    private int barWidth;
    private int gap;
    private int heightMultiplier = 2; // Adjust the multiplier to fit the bars within the panel height

    private static final int SPEED_UP_FACTOR = 100; // Speed up factor

    public QuickSortVisualization(int[] array, int panelWidth) {
        this.values = array;
        this.states = new int[values.length];
        calculateBarGap(panelWidth); // Calculate bar width and gap based on available space
        for (int i = 0; i < values.length; i++) {
            states[i] = -1;
        }
        Thread t = new Thread(this);
        t.start();
    }

    private void calculateBarGap(int availableWidth) {
        // Ensure minimum bar width and gap
        barWidth = Math.max(MIN_BAR_WIDTH, availableWidth / values.length - MIN_GAP);
        gap = Math.max(MIN_GAP, availableWidth / values.length - barWidth);
    }

    @Override
    public void run() {
        quickSort(values, 0, values.length - 1);
    }

    public void quickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int index = partition(arr, start, end);
        quickSort(arr, start, index - 1);
        quickSort(arr, index + 1, end);
    }

    public int partition(int[] arr, int start, int end) {
        for (int i = start; i < end; i++) {
            states[i] = 1;
            repaint();
            try {
                Thread.sleep(0, 250); // Decreased sleep time for faster visualization (0.25 microseconds)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int pivotValue = arr[end];
        int pivotIndex = start;
        states[pivotIndex] = 0;
        for (int i = start; i < end; i++) {
            if (arr[i] < pivotValue) {
                int temp = arr[i];
                arr[i] = arr[pivotIndex];
                arr[pivotIndex] = temp;
                pivotIndex++;
            }
        }
        int temp = arr[pivotIndex];
        arr[pivotIndex] = arr[end];
        arr[end] = temp;

        for (int i = start; i < end; i++) {
            if (i != pivotIndex) {
                states[i] = -1;
                repaint();
            }
        }

        return pivotIndex;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < values.length; i++) {
            int barHeight = values[i] * (heightMultiplier);
            if (states[i] == 0) {
                g.setColor(new Color(255, 51, 51)); // Red for pivot element
            } else if (states[i] == 1) {
                g.setColor(new Color(102, 255, 102)); // Light green for elements being compared
            } else {
                g.setColor(new Color(0, 0, 0)); // Black for other elements
            }
            g.fillRect(i * (barWidth + gap), getHeight() - barHeight, barWidth, barHeight);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        int panelWidth = (values.length * (barWidth + gap)) + gap;
        int maxHeight = 0;
        for (int value : values) {
            maxHeight = Math.max(maxHeight, value);
        }
        int panelHeight = maxHeight * heightMultiplier;
        return new Dimension(panelWidth, panelHeight);
    }
}
