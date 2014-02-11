package coursera.algorithms.C2_1;

public class Selection extends SortBase {

    @Override
    public String getName() {
        return "select";
    }

    @Override
    protected <T extends Comparable<T>> void doSort(T[] values, int start,
            int end) {
        for (int i = start; i < end; i++) {
            int minIndex = i;
            for (int j = i + 1; j < end; j++) {
                if (compare(values[j], values[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                exchange(values, i, minIndex);
            }
        }
    }
}
