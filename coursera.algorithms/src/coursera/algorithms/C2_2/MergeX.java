package coursera.algorithms.C2_2;

import java.util.Comparator;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

/**
 * use insert sort if element count is less than 7 test if the array in order
 * reduce the auxiliary copy
 * 
 * @author wyan
 * 
 */
public class MergeX {

    static final int INSERT_LIMIT = 7;

    public <T> void sort(T[] values, Comparator<T> c) {
        if (values == null)
            return;
        if (values.length < INSERT_LIMIT) {
            insertSort(values, 0, values.length - 1, c);
            return;
        }
        T[] aux = values.clone();
        sort(values, 0, values.length - 1, aux, c);
    }

    protected <T> void sort(T[] source, int start, int end, T[] target,
            Comparator<T> c) {
        if (start + INSERT_LIMIT >= end) {
            insertSort(source, start, end, c);
            return;
        }
        int mid = start + (end - start) / 2;
        sort(target, start, mid, source, c);
        sort(target, mid + 1, end, source, c);
        assert isSorted(target, start, mid, c);
        assert isSorted(target, mid + 1, end, c);

        // the largest of left part is less or equal to lest of right part, skip
        // the merge
        if (c.compare(target[mid], target[mid + 1]) <= 0) {
            System.arraycopy(target, start, source, start, end - start + 1);
        } else {
            merge(target, start, mid, end, source, c);
        }
        assert isSorted(source, start, end, c);
    }

    protected <T> void merge(T[] source, int start, int mid, int end,
            T[] target, Comparator<T> c) {

        int i = start;
        int j = mid + 1;
        int index = start;
        while (true) {
            int r = c.compare(source[i], source[j]);
            if (r <= 0) {
                target[index++] = source[i++];
                if (i > mid) {
                    break;
                }
            } else {
                target[index++] = source[j++];
                if (j > end) {
                    break;
                }
            }
        }

        while (i <= mid) {
            target[index++] = source[i++];
        }
        while (j <= end) {
            target[index++] = source[j++];
        }
        assert isSorted(target, start, end, c);
    }

    protected <T> void insertSort(T[] values, int start, int end,
            Comparator<T> c) {
        for (int i = start + 1; i <= end; i++) {
            for (int j = i; j > start; j--) {
                if (c.compare(values[j], values[j - 1]) < 0) {
                    T v = values[j - 1];
                    values[j - 1] = values[j];
                    values[j] = v;
                    continue;
                }
                break;
            }
        }
    }

    @Test
    public void testMergeSort() {
        for (int i = 0; i < 200; i++) {
            testMergeSort(i);
        }
    }

    @Test
    public void testInsertSort() {
        for (int i = 0; i < 18; i++) {
            testInsertSort(i);
        }
    }

    protected void testInsertSort(int size) {
        Random r = new Random();
        Comparator<String> c = new StringComparator();
        System.out.println(size);
        String[] values = new String[size];
        for (int i = 0; i < values.length; i++) {
            values[i] = String.valueOf(r.nextInt());
        }
        insertSort(values, 0, values.length - 1, c);
        Assert.assertTrue(isSorted(values, c));
    }

    protected void testMergeSort(int size) {
        Random r = new Random();
        Comparator<String> c = new StringComparator();
        System.out.println(size);
        String[] values = new String[size];
        for (int i = 0; i < values.length; i++) {
            values[i] = String.valueOf(r.nextInt());
        }
        sort(values, c);
        Assert.assertTrue(isSorted(values, c));
    }

    private static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    protected <T> boolean isSorted(T[] values, int start, int end,
            Comparator<T> c) {
        for (int i = start + 1; i <= end; i++) {
            if (c.compare(values[i - 1], values[i]) > 0) {
                return false;
            }
        }
        return true;
    }

    protected <T> boolean isSorted(T[] values, Comparator<T> c) {
        return isSorted(values, 0, values.length - 1, c);
    }

}
