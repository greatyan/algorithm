package coursera.algorithms.C2_1;

public class InsertionSentinel extends SortBase {

    @Override
    public String getName() {
        return "insertS";
    }

    @Override
    protected <T extends Comparable<T>> void doSort(T[] values, int start,
            int end) {
        // first one is the smallest
        for (int i = end - 1; i > start; i--) {
            if (compare(values[i], values[i - 1]) < 0) {
                exchange(values, i, i - 1);
            }
        }
        // insert without bounding test
        for (int i = start + 2; i < end; i++) {
            int j = i;
            while (compare(values[j], values[j - 1]) < 0) {
                exchange(values, j, j - 1);
                j--;
            }
        }
    }

}
