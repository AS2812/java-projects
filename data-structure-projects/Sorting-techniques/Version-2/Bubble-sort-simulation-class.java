import processing.core.PApplet;

public class BubbleSortVisualization extends PApplet {

    float[] values;
    int i = 0;
    int framesPerUpdate = 40; // Adjust the number of frames per update
    int []array;
    public BubbleSortVisualization(int[] randomArray)
    {
      this.array=randomArray;
    }

    public void settings() {
        size(displayWidth, displayHeight);
        values = new float[width];
        for (int i = 0; i < values.length; i++) {
            values[i] = random(height);
            //values[i] = noise(i/100.0)*height;
        }
    }

    public void draw() {
        background(0);

        // Check if sorting is complete
        if (i < values.length) {
            // Iterate through the array to perform sorting
            for (int k = 0; k < framesPerUpdate && i < values.length; k++) {
                for (int j = 0; j < values.length - i - 1; j++) {
                    float a = values[j];
                    float b = values[j + 1];
                    if (a > b) {
                        swap(values, j, j + 1);
                    }
                }
                // Increment the outer loop counter
                i++;
            }
        } else {
            // Sorting is complete
            println("Finished");
            noLoop();
        }

        // Draw the bars representing array elements
        for (int i = 0; i < values.length; i++) {
            stroke(255);
            line(i, height, i, height - values[i]);
        }
    }

    void swap(float[] arr, int a, int b) {
        float temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
