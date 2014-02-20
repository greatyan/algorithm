package coursera.algorithms.C2_1;

import org.junit.Test;

public class MinimumMoveSort {

    void sort(int[] values) {
        for (int i = values.length; i >= 1; i--) {
            int minValue = values[0];
            int index = 0;
            for (int j = 1; j < i; j++) {
                if (values[j] < minValue) {
                    minValue = values[j];
                    index = j;
                }
            }
            move(values, index);
        }
        for (int i = 0; i < values.length; i++) {
            System.out.print(values[i]);
            System.out.print(",");
        }
        System.out.println();
    }

    protected void move(int[] values, int index) {
        for (int i = index; i < values.length - 1; i++) {
            int t = values[index];
            values[index] = values[index + 1];
            values[index + 1] = t;
        }
    }

    @Test
    public void test() {
        sort(new int[] { 0, 1, 2, 3, 4, 5, 6 });
        sort(new int[] { 6, 5, 4, 3, 2, 1, 0 });
    }
}
