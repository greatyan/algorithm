package coursera.algorithms.C2_2;

import java.util.Comparator;

import org.junit.Assert;
import org.junit.Test;

public class IndexMergeSort {

    private static final int INSERT_LIMIT = 7;

    public <T> int[] sort(T[] values, Comparator<T> c) {
        int[] indexes = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            indexes[i] = i;
        }
        int[] aux = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            aux[i] = i;
        }
        sort(indexes, values, 0, indexes.length - 1, aux, c);
        return indexes;
    }

    protected <T> void sort(int[] source, T[] values, int start, int end,
            int[] target, Comparator<T> c) {

        // opt1: use insert sort
        if (start + INSERT_LIMIT >= end) {
            insertSort(source, values, start, end, c);
            return;
        }

        int mid = start + (end - start) / 2;
        sort(target, values, start, mid, source, c);
        sort(target, values, mid + 1, end, source, c);

        assert isSorted(target, values, start, mid, c);
        assert isSorted(target, values, mid, end, c);

        // opt2: return if already sorted
        if (c.compare(values[target[mid]], values[target[mid + 1]]) <= 0) {
            for (int i = start; i <= end; i++) {
                source[i] = target[i];
            }
            assert isSorted(source, values, start, end, c);
            return;
        }
        // opt3, always merge the result from source to target
        merge(target, values, start, mid, end, source, c);

        assert isSorted(source, values, start, end, c);

    }

    protected <T> void merge(int[] source, T[] values, int start, int mid,
            int end, int[] target, Comparator<T> c) {

        int i = start;
        int j = mid + 1;
        int index = start;
        while (true) {
            if (c.compare(values[source[i]], values[source[j]]) <= 0) {
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
    }

    protected <T> void insertSort(int[] indexes, T[] values, int start,
            int end, Comparator<T> c) {
        // set the first element to the minimum value
        for (int i = end; i > start; i--) {
            if (c.compare(values[indexes[i]], values[indexes[i - 1]]) < 0) {
                int v = indexes[i];
                indexes[i] = indexes[i - 1];
                indexes[i - 1] = v;
            }
        }
        //
        for (int i = start + 2; i <= end; i++) {
            int index = i;
            while (c.compare(values[indexes[index]], values[indexes[index - 1]]) < 0) {
                int v = indexes[index];
                indexes[index] = indexes[index - 1];
                indexes[index - 1] = v;
                index--;
            }
        }
    }

    protected <T> boolean isSorted(int[] index, T[] values, int start, int end,
            Comparator<T> c) {
        for (int i = start + 1; i <= end; i++) {
            if (c.compare(values[index[i - 1]], values[index[i]]) > 0) {
                return false;
            }
        }
        return true;
    }

    protected <T> boolean isSorted(int[] index, T[] values, Comparator<T> c) {
        return isSorted(index, values, 0, index.length - 1, c);
    }

    @Test
    public void testInsertSort() {
        Comparator<String> c = new StringComparator();
        String[] values = new String[100];
        for (int i = 0; i < values.length; i++) {
            values[i] = String.valueOf(Math.random());
        }

        int[] index = new int[values.length];
        for (int i = 0; i < index.length; i++) {
            index[i] = i;
        }
        insertSort(index, values, 0, values.length - 1, c);
        Assert.assertTrue(isSorted(index, values, c));
    }

    @Test
    public void testMergeSort() {
        Comparator<String> c = new StringComparator();
        String[] values = new String[200];
        for (int i = 0; i < values.length; i++) {
            values[i] = String.valueOf(Math.random());
        }
        int[] indexes = sort(values, c);
        for (int i = 0; i < indexes.length; i++) {
            System.out.println(values[indexes[i]]);
        }
        Assert.assertTrue(isSorted(indexes, values, c));
    }

    private static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    private <T> void debugPrint(int[] index, T[] values, int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.println(values[index[i]]);
        }
        System.out.println();
        return;
    }

}
