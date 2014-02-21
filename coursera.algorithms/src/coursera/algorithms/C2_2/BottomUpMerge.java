package coursera.algorithms.C2_2;

import org.junit.Test;

public class BottomUpMerge {

    public void sort(int[] values) {

        int[] aux = new int[values.length];
        int size = 1;
        while (size < values.length) {
            int offset = 0;
            while (offset < values.length) {
                merge(values, offset, size, aux);
                offset += 2 * size;
            }
            size *= 2;
        }

        if (isSorted(values)) {
            System.out.println("Sorted");
        } else {
            System.out.println("Failed");
        }
    }

    protected void merge(int[] values, int off, int size, int[] aux) {

        int mergeSize = size * 2;
        if (mergeSize + off > values.length) {
            mergeSize = values.length - off;
        }
        // assert mergeSize > size;

        int i = off;
        int j = off + size;
        int end1 = off + size;
        int end2 = off + mergeSize;
        int index = 0;
        while (index < mergeSize) {
            if (i >= end1) {
                aux[index++] = values[j++];
            } else if (j >= end2) {
                aux[index++] = values[i++];
            } else {
                if (values[i] > values[j]) {
                    aux[index++] = values[j++];
                } else {
                    aux[index++] = values[i++];
                }
            }
        }

        // copy back
        i = off;
        index = 0;
        while (index < mergeSize) {
            values[i++] = aux[index++];
        }
    }

    boolean isSorted(int[] values) {
        for (int i = 1; i < values.length; i++) {
            if (values[i - 1] > values[i]) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {

        sort(new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
        sort(new int[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 });
    }

}
