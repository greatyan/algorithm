package coursera.algorithms.C2_1;

abstract public class SortBase {

    protected int compareCount;
    protected int exchangeCount;
    protected int sortTime;

    abstract public String getName();

    protected <T extends Comparable<T>> int compare(T v1, T v2) {
        compareCount++;
        return v1.compareTo(v2);
    }

    protected <T extends Comparable<?>> void exchange(T[] values, int src,
            int tgt) {
        exchangeCount++;
        T v = values[src];
        values[src] = values[tgt];
        values[tgt] = v;
    }

    public <T extends Comparable<T>> void sort(T[] values) {
        exchangeCount = 0;
        compareCount = 0;
        long start = System.currentTimeMillis();
        doSort(values, 0, values.length);
        long end = System.currentTimeMillis();
        sortTime = (int) (end - start);
    }

    abstract protected <T extends Comparable<T>> void doSort(T[] values,
            int start, int end);

    public int getExchangeCount() {
        return exchangeCount;
    }

    public int getCompareCount() {
        return compareCount;
    }

    public int getSortTime() {
        return sortTime;
    }

}
