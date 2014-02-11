package coursera.algorithms.C2_1;

import org.junit.Assert;
import org.junit.Test;

public class ThreeSort {

    public <T extends Comparable<T>> T[] sort(T[] v) {
        if (v[0].compareTo(v[1]) < 0) {
            if (v[1].compareTo(v[2]) < 0) {
                // unchanged 0, 1, 2
            } else {
                if (v[0].compareTo(v[2]) < 0) {
                    // 0, 2, 1
                    swap(v, 1, 2);
                } else {// 2,0,1
                    swap(v, 0, 2);
                    swap(v, 1, 2);
                }
            }
        } else {
            if (v[0].compareTo(v[2]) < 0) {
                // 1,0,2
                swap(v, 0, 1);
            } else {
                if (v[1].compareTo(v[2]) < 0) {
                    // 1, 2, 0
                    swap(v, 0, 1);
                    swap(v, 1, 2);
                } else {
                    // 2,1,0
                    swap(v, 0, 2);
                }
            }
        }
        return v;
    }

    public <T extends Comparable<T>> T[] sort1(T[] v) {
        if (v[0].compareTo(v[1]) > 0) {
            swap(v, 0, 1);
        }
        if (v[1].compareTo(v[2]) > 0) {
            swap(v, 2, 1);
        }

        if (v[0].compareTo(v[1]) > 0) {
            swap(v, 0, 1);
        }
        return v;
    }

    private <T> void swap(T[] values, int i, int j) {
        T v = values[i];
        values[i] = values[j];
        values[j] = v;
    }

    @Test
    public void test() {

        Assert.assertArrayEquals(new String[] { "a", "b", "c" },
                sort(new String[] { "a", "b", "c" }));
        Assert.assertArrayEquals(new String[] { "a", "b", "c" },
                sort(new String[] { "a", "c", "b" }));
        Assert.assertArrayEquals(new String[] { "a", "b", "c" },
                sort(new String[] { "b", "a", "c" }));
        Assert.assertArrayEquals(new String[] { "a", "b", "c" },
                sort(new String[] { "b", "c", "a" }));
        Assert.assertArrayEquals(new String[] { "a", "b", "c" },
                sort(new String[] { "c", "a", "b" }));
        Assert.assertArrayEquals(new String[] { "a", "b", "c" },
                sort(new String[] { "c", "b", "a" }));

    }

    @Test
    public void test1() {

        Assert.assertArrayEquals(new String[] { "a", "b", "c" },
                sort1(new String[] { "a", "b", "c" }));
        Assert.assertArrayEquals(new String[] { "a", "b", "c" },
                sort1(new String[] { "a", "c", "b" }));
        Assert.assertArrayEquals(new String[] { "a", "b", "c" },
                sort1(new String[] { "b", "a", "c" }));
        Assert.assertArrayEquals(new String[] { "a", "b", "c" },
                sort1(new String[] { "b", "c", "a" }));
        Assert.assertArrayEquals(new String[] { "a", "b", "c" },
                sort1(new String[] { "c", "a", "b" }));
        Assert.assertArrayEquals(new String[] { "a", "b", "c" },
                sort1(new String[] { "c", "b", "a" }));

    }

}
