package coursera.algorithms.C2_1;

import java.util.Arrays;
import java.util.Random;

import coursera.algorithms.C2_2.Merge;
import coursera.algorithms.C2_2.MergeInsert;

public class SortTest {

    public static void main(String[] args) {
        int[] sizes = new int[] { 4096 * 16, 8192 * 16, 16384 * 16 };
        SortBase[] sorts = new SortBase[] { new Merge(), new MergeInsert() };

        new SortTest().testRandom(sizes, sorts);
        // new SortTest().testWorst(sizes, sorts);
        // new SortTest().testBest(sizes, sorts);
        // new SortTest().testHSorted(sizes, sorts);
    }

    private void testRandom(int[] sizes, SortBase[] sorts) {

        Integer[][] testValues = new Integer[sizes.length][];
        System.out.println("random");
        for (int i = 0; i < sizes.length; i++) {
            testValues[i] = createRandomValues(sizes[i]);
        }
        test(testValues, sorts);
    }

    private void testWorst(int[] sizes, SortBase[] sorts) {

        Integer[][] testValues = new Integer[sizes.length][];
        System.out.println("worst");
        for (int i = 0; i < sizes.length; i++) {
            testValues[i] = createWorstValues(sizes[i]);
        }
        test(testValues, sorts);
    }

    private void testBest(int[] sizes, SortBase[] sorts) {

        Integer[][] testValues = new Integer[sizes.length][];
        System.out.println("best");
        for (int i = 0; i < sizes.length; i++) {
            testValues[i] = createBestValues(sizes[i]);
        }
        test(testValues, sorts);
    }

    private void testHSorted(int[] sizes, SortBase[] sorts) {

        Integer[][] testValues = new Integer[sizes.length][];
        System.out.println("h-sort");
        for (int i = 0; i < sizes.length; i++) {
            testValues[i] = createHSortedValues(sizes[i]);
        }
        test(testValues, sorts);
    }

    private void test(Integer[][] testValues, SortBase[] sorts) {
        System.out.println("name\tsize\ttime\tmove\tcompare");
        for (Integer[] values : testValues) {
            for (SortBase sort : sorts) {
                Integer[] vs = Arrays.copyOf(values, values.length);
                sort.sort(vs);
                System.out
                        .println(sort.getName() + "\t" + vs.length + "\t"
                                + sort.getSortTime() + "\t"
                                + sort.getExchangeCount() + "\t"
                                + sort.getCompareCount() + "\t\t"
                                + isSorted(vs));
            }
        }
    }

    private Integer[] createRandomValues(int size) {
        Random r = new Random();
        Integer[] values = new Integer[size];
        for (int i = 0; i < size; i++) {
            values[i] = r.nextInt();
        }
        return values;
    }

    private static Integer[] createBestValues(int count) {
        Integer[] values = new Integer[count];
        for (int i = 0; i < count; i++) {
            values[i] = i;
        }
        return values;
    }

    private static Integer[] createWorstValues(int count) {
        Integer[] values = new Integer[count];
        for (int i = 0; i < count; i++) {
            values[i] = count - i;
        }
        return values;
    }

    private static Integer[] createHSortedValues(int count) {
        Integer[] values = new Integer[count];
        int v = 0;
        for (int i = 0; i < 10; i++) {
            int index = i;
            while (index < count) {
                values[index] = v++;
                index += 10;
            }
        }
        return values;
    }

    boolean isSorted(Integer[] values) {
        for (int i = 1; i < values.length; i++) {
            if (values[i - 1].compareTo(values[i]) > 0) {
                return false;
            }
        }
        return true;
    }
}
