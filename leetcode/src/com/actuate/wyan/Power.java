package com.actuate.wyan;

import org.junit.Test;

public class Power {

    @Test
    public void test() {
        System.out.println(new Solution().pow(1.00000, -2147483648));
        System.out.println(new Solution().pow(-1.00000, -2147483648));
        System.out.println(new Solution().pow(-0.5, -2147483648));
        System.out.println(new Solution().pow(2, 2));
        System.out.println(new Solution().pow(2, 3));
        System.out.println(new Solution().pow(2, 4));
        System.out.println(new Solution().pow(2, -2));
        System.out.println(new Solution().pow(2, -3));
        System.out.println(new Solution().pow(2, -4));
        System.out.println(new Solution().pow(8.88023, 3));
        //Output:     8.88023
    }

    public class Solution {

        public double powerRecursive(double x, int n) {
            if (n == 0) {
                return 1;
            }

            if (n == 1) {
                return x;
            }

            if (n % 2 == 0) {
                return powerRecursive(x * x, n / 2);
            }
            return x * powerRecursive(x * x, n / 2);
        }

        public double pow(double x, int n) {

            boolean useRecursive = true;
            if (useRecursive) {
                double v = powerRecursive(x, n);
                if (n < 0) {
                    return 1.0 / v;
                }
                return v;
            }
            return powerLoop(x, n);
        }

        protected double powerLoop(double x, int n) {

            boolean negativeN = false;
            boolean negativeX = false;
            long longN = n;

            if (n < 0) {
                negativeN = true;
                longN = -longN;
            }
            if (Double.compare(x, 0) < 0) {
                negativeX = true;
                x = -x;
            }

            double v = pow(x, longN);

            if (negativeX) {
                if (n % 2 == 1) {
                    v = -v;
                }
            }

            if (negativeN) {
                if (Double.compare(v, 0) == 0) {
                    if (x > 0) {
                        return Double.POSITIVE_INFINITY;
                    }
                    return Double.NEGATIVE_INFINITY;
                }
                return 1.0 / v;
            }

            return v;
        }

        private double pow(double x, long n) {

            assert Double.compare(x, 0) >= 0;
            assert n >= 0;

            if (n == 0) {
                return 1;
            }

            if (Double.compare(x, 0) == 0) {
                return 0;
            }

            if (Double.compare(x, 1) == 0) {
                return 1;
            }

            double v = x;
            double result = 1.0;
            while (n > 0) {
                if (n % 2 == 1) {
                    result *= v;
                }
                v = v * v;
                n = n / 2;
            }
            return result;
        }
    }

}
