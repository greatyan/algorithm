package coursera.algorithms.C2_1;

import org.junit.Test;

public class Sort5 {

    void sort(int v1, int v2, int v3, int v4, int v5) {
        if (v1 < v2) {
            int t = v1;
            v1 = v2;
            v2 = t;
        }
        if (v3 < v4) {
            int t = v3;
            v3 = v4;
            v4 = t;
        }

        if (v1 < v3) {
            int t = v3;
            v3 = v1;
            v1 = t;
        }
        if (v2 < v4) {
            int t = v4;
            v4 = v2;
            v2 = t;
        }
        if (v1 < v5) {
            int t = v1;
            v1 = v5;
            v5 = t;
        }
        if (v4 < v5) {
            int t = v4;
            v4 = v5;
            v5 = t;
        }
        if (v2 < v3) {
            int t = v2;
            v2 = v3;
            v3 = t;
        }
        if (v2 < v4) {
            int t = v4;
            v4 = v2;
            v2 = t;
        }
        if (v3 < v4) {
            int t = v3;
            v3 = v4;
            v4 = t;
        }
        System.out.print(v1);
        System.out.print(v2);
        System.out.print(v3);
        System.out.print(v4);
        System.out.print(v5);
        System.out.println();
    }

    @Test
    public void test() {
        sort(0, 0, 0, 0, 0);
        sort(0, 0, 0, 0, 1);
        sort(0, 0, 0, 1, 0);
        sort(0, 0, 0, 1, 1);
        sort(0, 0, 1, 0, 0);
        sort(0, 0, 1, 0, 1);
        sort(0, 0, 1, 1, 0);
        sort(0, 0, 1, 1, 1);
        sort(0, 1, 0, 0, 0);
        sort(0, 1, 0, 0, 1);
        sort(0, 1, 0, 1, 0);
        sort(0, 1, 0, 1, 1);
        sort(0, 1, 1, 0, 0);
        sort(0, 1, 1, 0, 1);
        sort(0, 1, 1, 1, 0);
        sort(0, 1, 1, 1, 1);
        sort(1, 0, 0, 0, 0);
        sort(1, 0, 0, 0, 1);
        sort(1, 0, 0, 1, 0);
        sort(1, 0, 0, 1, 1);
        sort(1, 0, 1, 0, 0);
        sort(1, 0, 1, 0, 1);
        sort(1, 0, 1, 1, 0);
        sort(1, 0, 1, 1, 1);
        sort(1, 1, 0, 0, 0);
        sort(1, 1, 0, 0, 1);
        sort(1, 1, 0, 1, 0);
        sort(1, 1, 0, 1, 1);
        sort(1, 1, 1, 0, 0);
        sort(1, 1, 1, 0, 1);
        sort(1, 1, 1, 1, 0);
        sort(1, 1, 1, 1, 1);
    }

}
