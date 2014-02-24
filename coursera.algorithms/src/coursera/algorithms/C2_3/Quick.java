package coursera.algorithms.C2_3;

import static coursera.algorithms.SortUtil.createRandomStrings;
import static coursera.algorithms.SortUtil.isSorted;
import static coursera.algorithms.SortUtil.printValues;

import java.util.Comparator;

import org.junit.Assert;
import org.junit.Test;

import coursera.algorithms.StringComparator;

public class Quick {

    private final int INSERT_LIMIT = 8;
    private boolean enableInsertSort = false;
    private boolean enableSeeding = false;
    private int accessCount = 0;
    private int compareCount = 0;
    private int exchangeCount = 0;

    private void resetCount() {
        accessCount = 0;
        compareCount = 0;
        exchangeCount = 0;
    }

    public void printCount() {
        System.out.println("access :" + accessCount);
        System.out.println("compare:" + compareCount);
        System.out.println("exchange:" + exchangeCount);

    }

    public <T> void sort(T[] values, Comparator<T> c) {
        resetCount();
        sort(values, 0, values.length - 1, c);
    }

    protected <T> void sort(T[] values, int start, int end, Comparator<T> c) {

        if (enableInsertSort && start + INSERT_LIMIT >= end) {
            insertSort(values, start, end, c);
            return;
        }

        if (start >= end) {
            return;
        }

        // swap the seed to the first element
        int mid = partition(values, start, end, c);
        sort(values, start, mid - 1, c);
        sort(values, mid + 1, end, c);
    }

    protected <T> void insertSort(T[] values, int start, int end,
            Comparator<T> c) {
        for (int i = end - 1; i >= start; i--) {
            if (less(values, i + 1, i, c)) {
                swap(values, i + 1, i);
            }
        }
        for (int i = start + 1; i <= end; i++) {
            int k = i;
            while (less(values, k, k - 1, c)) {
                swap(values, k, k - 1);
                k--;
            }
        }
    }

    private <T> int partition(T[] values, int start, int end, Comparator<T> c) {
        if (enableSeeding) {
            selectSeed(values, start, end, c);
        }
        int low = start + 1;
        int high = end;
        while (true) {
            while (less(values, low, start, c)) {
                low++;
                if (low > high) {
                    break;
                }
            }
            while (less(values, start, high, c)) {
                high--;
                if (high < low) {
                    break;
                }
            }
            // switch i,
            if (high > low) {
                swap(values, high, low);
                high--;
                low++;
            } else {
                break;
            }
        }
        // high points to the end of left partition
        if (start != high) {
            swap(values, start, high);
        }
        return high;
    }

    protected <T> void selectSeed(T[] values, int low, int high, Comparator<T> c) {

        // only two of the values
        if (low + 1 >= high) {
            return;
        }

        // use the middle value of low, high, (high+low)/2.
        int mid = low + (high - low) / 2;

        if (less(values, low, mid, c)) {
            if (less(values, mid, high, c)) {
                // low, mid, high
                swap(values, low, mid);
                return;
            }
            if (less(values, low, high, c)) {
                // high, low, mid
                return;
            }
            // low, high, mid
            swap(values, low, high);
            return;
        }

        if (less(values, low, high, c)) {
            // mid, low, high
            return;
        }
        if (less(values, mid, high, c)) {
            // mid, high, low
            swap(values, low, high);
            return;
        }
        // high, mid, low
        swap(values, low, mid);
    }

    protected <T> void swap(T[] values, int i, int j) {
        accessCount += 4;
        exchangeCount += 1;
        T v = values[i];
        values[i] = values[j];
        values[j] = v;
    }

    protected <T> boolean less(T[] values, int i, int j, Comparator<T> c) {
        accessCount += 2;
        compareCount++;
        return c.compare(values[i], values[j]) < 0;
    }

    @Test
    public void testQSort() {

        long start = System.currentTimeMillis();
        String[] seeds = createRandomStrings(1000000);
        for (int i = 0; i < 100; i++) {
            String[] values = seeds.clone();
            // printValues(values);
            sort(values, new StringComparator());
            if (!isSorted(values)) {
                printValues(values);
                Assert.fail();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    @Test
    public void testQSortFailedCase() {
        String[] values = new String[] { "0", "3", "4", "0", "1", "2" };
        sort(values, new StringComparator());
        if (!isSorted(values)) {
            printValues(values);
            Assert.fail();
        }
    }

}
