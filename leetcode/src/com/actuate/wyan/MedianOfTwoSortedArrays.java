package com.actuate.wyan;

import org.junit.Assert;
import org.junit.Test;

public class MedianOfTwoSortedArrays {

    @Test
    public void test() {
        Assert.assertEquals(2.5, new Solution().findMedianSortedArrays(
                new int[] { 1 }, new int[] { 2, 3, 4 }), 0);

        Assert.assertEquals(
                2.5,
                new Solution().findMedianSortedArrays(new int[] {}, new int[] {
                        2, 3 }), 0);
        Assert.assertEquals(1, new Solution().findMedianSortedArrays(
                new int[] {}, new int[] { 1 }), 0);
        Assert.assertEquals(
                4.0,
                new Solution().findMedianSortedArrays(new int[] { 0, 1, 2, 3,
                        4, 5, 6, 7 }, new int[] { 8 }), 0);
        Assert.assertEquals(4.0, new Solution().findMedianSortedArrays(
                new int[] { 8 }, new int[] { 0, 1, 2, 3, 4, 5, 6, 7 }), 0);
        Assert.assertEquals(3.5, new Solution().findMedianSortedArrays(
                new int[] { 1, 2, 3 }, new int[] { 4, 5, 6 }), 0);
    }

    static public class Solution {

        public double findMedianSortedArrays(int A[], int B[]) {
            // return BFfind(A, B);
            return bfind(A, B);
        }

        public double BFfind(int[] A, int B[]) {
            int[] values = merge(A, B);
            if (values == null || values.length == 0) {
                return 0;
            }

            if (values.length % 2 == 0) {
                return ((double) values[values.length / 2 - 1] + values[values.length / 2]) / 2;
            }
            return values[values.length / 2];

        }

        private int[] merge(int[] A, int[] B) {
            if (A == null || A.length == 0) {
                return B;
            }
            if (B == null || B.length == 0) {
                return A;
            }
            int[] values = new int[A.length + B.length];
            int a = 0;
            int b = 0;
            for (int i = 0; i < values.length; i++) {
                if (a >= A.length) {
                    values[i] = B[b];
                    b++;
                } else if (b >= B.length) {
                    values[i] = A[a];
                    a++;
                } else if (A[a] < B[b]) {
                    values[i] = A[a];
                    a++;
                } else {
                    values[i] = B[b];
                    b++;
                }
            }
            return values;
        }

        private double getMedian(int[] a) {
            int index = getMedianIndex(a.length);
            if (a.length % 2 == 1) {
                return a[index];
            }
            return (a[index] + a[index + 1]) / 2.0;
        }

        public double bfind(int[] a, int[] b) {
            if (a.length == 0) {
                return getMedian(b);
            } else if (b.length == 0) {
                return getMedian(a);
            }
            int kth = getMedianIndex(a.length + b.length);
            int index = bfind(a, 0, a.length - 1, b, kth);
            if (index != -1) {
                return getMedian(a, b, index);
            }
            index = bfind(b, 0, b.length - 1, a, kth);
            if (index != -1) {
                return getMedian(b, a, index);
            }
            assert false;
            return Double.NaN;
        }

        /**
         * return the index of median
         * 
         * return 0 for 0. return 0 for 0 1.
         * 
         * @param length
         * @return
         */
        protected int getMedianIndex(int length) {
            if (length % 2 == 0) {
                return length / 2 - 1;
            }
            return length / 2;
        }

        /**
         * 
         * @param kth
         * @param index
         * @return
         */
        protected int getBIndex(int kth, int index) {
            return kth - index - 1;
        }

        protected double getMedian(int[] a, int b[], int index) {

            // odd number, return a directly.
            if ((a.length + b.length) % 2 == 1) {
                return a[index];
            }

            // even number, find the one larger than a, average.
            int kth = getMedianIndex(a.length + b.length);
            // count of element in a array before median
            // b_index is the one in B which is larger than a[a_index]
            int b_index = getBIndex(kth, index) + 1;
            // the next value should be a[index+1] or b[b_index]
            int value = 0;
            if (index + 1 >= a.length) {
                value = b[b_index];
            } else if (b_index < 0 || b_index >= b.length) {
                value = a[index + 1];
            } else {
                // smaller one in a[index+1], b[b_index];
                value = Math.min(a[index + 1], b[b_index]);
            }
            return (a[index] + value) / 2.0;
        }

        public int bfind(int[] a, int start_a, int end_a, int[] b, int kth) {
            if (start_a > end_a) {
                return -1;
            }
            int mid_a = start_a + (end_a - start_a) / 2;
            int ret = isKth(a, mid_a, b, kth);
            if (ret == 0) {
                return mid_a;
            }
            if (ret < 0) {
                return bfind(a, start_a, mid_a - 1, b, kth);
            }
            return bfind(a, mid_a + 1, end_a, b, kth);
        }

        /**
         * test if a[a_index] is the kth element in a and b.
         * 
         * @param a
         * @param a_index
         * @param b
         * @param kth
         * @return 0, a_index is the kth element. -1, move to left part 1, move
         *         to right part
         */
        private int isKth(int[] a, int a_index, int[] b, int kth) {

            // b_index should be the first element which less than a[a_index]
            int b_index = getBIndex(kth, a_index);

            // all b should larger than a[a_index]
            if (b_index == -1 && a[a_index] <= b[0]) {
                return 0;
            }
            // all b should less than a[a_index]
            if (b_index == b.length - 1 && a[a_index] >= b[b.length - 1]) {
                return 0;
            }
            // a_index is great than k, move left
            if (b_index < 0) {
                return -1;
            }
            // a_index + b.length is smaller than k, move rigth
            if (b_index >= b.length - 1) {
                return 1;
            }
            // move right
            if (a[a_index] < b[b_index]) {
                return 1;
            }
            // move left
            if (a[a_index] > b[b_index + 1]) {
                return -1;
            }
            // a
            return 0;
        }
    }
}
