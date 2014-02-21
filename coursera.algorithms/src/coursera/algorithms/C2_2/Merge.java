package coursera.algorithms.C2_2;

import java.util.Arrays;

import coursera.algorithms.C2_1.SortBase;

public class Merge extends SortBase {

    @Override
    public String getName() {
        return "merge";
    }

    @Override
    protected <T extends Comparable<T>> void doSort(T[] values, int start,
            int end) {
        T[] aux = Arrays.copyOf(values, end - start);
        doMerge(values, start, end, aux, 0);
    }

    protected <T extends Comparable<T>> void doMerge(T[] src, int srcStart,
            int srcEnd, T[] tgt, int tgtStart) {

        int size = srcEnd - srcStart;
        if (size <= 1) {
            return;
        }
        int leftSize = size / 2;
        doMerge(src, srcStart, srcStart + leftSize, tgt, tgtStart);
        doMerge(src, srcStart + leftSize, srcEnd, tgt, tgtStart + leftSize);
        // merge src to tgt
        int leftIndex = srcStart;
        int rightIndex = srcStart + leftSize;
        int leftEnd = srcStart + leftSize;
        int rightEnd = srcEnd;
        int index = tgtStart;
        while (leftIndex < leftEnd && rightIndex < rightEnd) {
            int r = compare(src[leftIndex], src[rightIndex]);
            if (r > 0) {
                tgt[index] = src[rightIndex];
                rightIndex++;
            } else {
                tgt[index] = src[leftIndex];
                leftIndex++;
            }
            exchangeCount++;
            index++;
        }
        while (leftIndex < leftEnd) {
            tgt[index] = src[leftIndex];
            exchangeCount++;
            leftIndex++;
            index++;
        }
        while (rightIndex < rightEnd) {
            tgt[index] = src[rightIndex];
            exchangeCount++;
            rightIndex++;
            index++;
        }
        // copy back to src
        for (int i = 0; i < size; i++) {
            exchangeCount++;
            src[srcStart + i] = tgt[tgtStart + i];
        }
    }
}
