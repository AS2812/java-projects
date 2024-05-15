import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GUIsimulation extends JPanel {
    private int[] array;
    private int delay = 1; // Delay in milliseconds between iterations

    public GUIsimulation(int[] array) {
        this.array = array;
        setPreferredSize(new Dimension(800, 600));
    }

    public void startSorting() {
        new Thread(() -> {
            quicksort(array, 0, array.length - 1);
            repaint();
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (array != null) {
            int barWidth = Math.max(1, getWidth() / array.length);
            for (int i = 0; i < array.length; i++) {
                int barHeight = (int) (((double) array[i] / array.length) * getHeight());
                int x = i * barWidth;
                int y = getHeight() - barHeight;
                g.setColor(Color.BLUE);
                g.fillRect(x, y, barWidth, barHeight);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, barWidth, barHeight);
            }
        }
    }

    private void delay() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void quicksort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quicksort(array, low, pi - 1);
            quicksort(array, pi + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        repaint();
        delay();
    }

    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size);
        }
        return array;
    }
}
