package coursera.algorithms.C2_1;

public class Shell extends SortBase {

    static final int[] GAPS = new int[] { 1, 4, 10, 23, 57, 132, 301, 701 };

    @Override
    public String getName() {
        return "shell";
    }

    @Override
    protected <T extends Comparable<T>> void doSort(T[] values, int start,
            int end) {

        for (int i = GAPS.length - 1; i >= 0; i--) {
            doSort(values, start, end, GAPS[i]);
        }
    }

    private <T extends Comparable<T>> void doSort(T[] values, int start,
            int end, int gap) {

        for (int i = 0; i < gap; i++) {
            int first = start + i ;
            for (int j = first + gap; j < end; j += gap) {
                int index = j;
                int prev = index - gap;
                while (compare(values[index], values[prev]) < 0) {
                    exchange(values, index, prev);
                    index = prev;
                    prev = index - gap;
                    if (prev < first) {
                        break;
                    }
                }
            }
        }
    }
}
