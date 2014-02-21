package coursera.algorithms.C2_2;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class Inversions {

    public int getInversionCount(int[] values) {
        int[] index = new int[values.length];
        for (int i = 0; i < index.length; i++) {
            index[i] = i;
        }
        // sort the array with merge index
        int count = sort(index, values);
        isSorted(index, values, 0, index.length - 1);
        return count;
    }

    int sort(int[] index, int[] values) {
        int[] tgt = index.clone();
        return sort(index, values, 0, index.length - 1, tgt);
    }

    int sort(int[] source, int[] values, int start, int end, int[] target) {
        if (start == end) {
            return 0;
        } else if (start + 1 == end) {
            if (values[source[start]] > values[source[end]]) {
                int v = source[start];
                source[start] = source[end];
                source[end] = v;
                return 1;
            }
            return 0;
        }
        int mid = start + (end - start + 1) / 2;
        int count1 = sort(source, values, start, mid, target);
        assert isSorted(source, values, start, mid);
        int count2 = sort(source, values, mid + 1, end, target);
        assert isSorted(source, values, mid + 1, end);
        int count3 = merge(source, values, start, mid, end, target);
        for (int i = start; i <= end; i++) {
            source[i] = target[i];
        }
        assert isSorted(source, values, start, end);
        return count1 + count2 + count3;
    }

    int merge(int[] source, int[] values, int start, int mid, int end,
            int[] target) {
        int count = 0;
        int i = start;
        int j = mid + 1;
        for (int index = start; index <= end; index++) {
            if (i > mid) {
                target[index] = source[j++];
            } else if (j > end) {
                target[index] = source[i++];
            } else if (values[source[i]] > values[source[j]]) {
                target[index] = source[j++];
                count += (j - index) - 1;
            } else {
                target[index] = source[i++];
            }
        }
        return count;
    }

    int slowCount(int[] values) {
        int count = 0;
        for (int i = 0; i < values.length; i++) {
            for (int j = i + 1; j < values.length; j++) {
                if (values[i] > values[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    @Test
    public void test() {
        Random r = new Random();

        for (int s = 3; s < 100; s++) {
            int[] values = new int[s];
            for (int t = 0; t < 5; t++) {
                for (int i = 0; i < values.length; i++) {
                    values[i] = r.nextInt(values.length);
                }
                int c1 = slowCount(values);
                int c2 = getInversionCount(values);
                if (c1 != c2) {
                    for (int j = 0; j < values.length; j++) {
                        System.out.println(values[j]);
                    }
                }
                Assert.assertEquals(c1, c2);
            }
        }
    }

    private boolean isSorted(int[] index, int[] values, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            if (values[index[i - 1]] > values[index[i]]) {
                return false;
            }
        }
        return true;
    }

}
