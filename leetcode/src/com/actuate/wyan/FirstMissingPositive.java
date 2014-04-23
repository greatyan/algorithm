package com.actuate.wyan;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class FirstMissingPositive {

    @Test
    public void test() {
        Assert.assertEquals(1,
                new Solution().firstMissingPositive(new int[] {}));
        Assert.assertEquals(3, new Solution().firstMissingPositive(new int[] {
                0, 2, 2, 1, 1 }));
        Assert.assertEquals(3,
                new Solution().firstMissingPositive(new int[] { 1, 2, 0 }));
        Assert.assertEquals(2,
                new Solution().firstMissingPositive(new int[] { 3, 4, -1, 1 }));
        Assert.assertEquals(3,
                new Solution().firstMissingPositive(new int[] { 1, 2 }));
    }

    public class Solution {
        public int firstMissingPositive(int[] A) {
            return new SolutionN().firstMissingPositive(A);
        }

        // the first missing value must in 1..N. iterator all the values, if
        // they are in 1..N, set a flag to indicate it exist. scan all the
        // flags, to find first missing.
        public class SolutionNWithNSpace {
            /**
             * 
             * @param A
             * @return
             */
            public int firstMissingPositive(int[] A) {

                if (A.length == 0) {
                    return 1;
                }
                int[] values = new int[A.length];

                for (int i = 0; i < A.length; i++) {
                    int index = A[i] - 1;
                    if (index >= 0 && index < A.length) {
                        values[index] = A[i];
                    }
                }

                for (int i = 0; i < A.length; i++) {
                    if (values[i] != i + 1) {
                        return i + 1;
                    }
                }
                return A.length + 1;
            }
        }

        public class SolutionN {
            /**
             * 
             * @param A
             * @return
             */
            public int firstMissingPositive(int[] A) {

                if (A.length == 0) {
                    return 1;
                }

                for (int i = 0; i < A.length; i++) {
                    swap(A, i);
                }

                for (int i = 0; i < A.length; i++) {
                    if (A[i] != i + 1) {
                        return i + 1;
                    }
                }
                return A.length + 1;
            }

            private void swap(int[] A, int i) {
                int index = A[i] - 1;
                while (index >= 0 && index < A.length) {
                    // save the value in target position
                    int nextIndex = A[index] - 1;
                    A[index] = index + 1;
                    if (nextIndex == index) {
                        break;
                    }
                    index = nextIndex;
                }
            }
        }

        public class SolutionNLOGN {
            public int firstMissingPositive(int[] A) {

                if (A == null || A.length == 0) {
                    return 1;
                }
                Arrays.sort(A);
                // skip all negative inter
                int index = 0;
                while (A[index] <= 0) {
                    index++;
                    if (index >= A.length) {
                        return 1;
                    }
                }
                // now A is the first positive integer
                if (A[index] != 1) {
                    return 1;
                }
                // A[index] == 1, skip it
                index++;
                // find the first gap
                while (index < A.length) {
                    if (A[index] > A[index - 1] + 1) {
                        return A[index - 1] + 1;
                    }
                    index++;
                }
                // return the last one
                return A[A.length - 1] + 1;
            }
        }
    }
}
