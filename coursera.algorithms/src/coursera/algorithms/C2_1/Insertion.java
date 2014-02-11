package coursera.algorithms.C2_1;

public class Insertion extends SortBase {

    @Override
    public String getName() {
        return "insert";
    }

    @Override
    protected <T extends Comparable<T>> void doSort(T[] values, int start,
            int end) {
        for (int i = start + 1; i < end; i++) {
            for (int j = i; j > start; j--) {
                // find the position in start to i
                if (compare(values[j], values[j - 1]) < 0) {
                    exchange(values, j, j - 1);
                    continue;
                }
                break;
            }
        }
    }
}
