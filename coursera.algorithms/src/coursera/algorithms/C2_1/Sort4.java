package coursera.algorithms.C2_1;

import org.junit.Test;

public class Sort4 {

    void sort(int v1, int v2, int v3, int v4) {
        if (v1 < v2) {
            int t = v2;
            v2 = v1;
            v1 = t;
        }
        if (v3 < v4) {
            int t = v4;
            v4 = v3;
            v3 = t;
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
        if (v2 < v3) {
            int t = v2;
            v2 = v3;
            v3 = v2;
        }

        System.out.println(v1 + "," + v2 + "," + v3 + "," + v4);
    }

    @Test
    public void test() {
        sort(0, 0, 0, 0);
        sort(0, 0, 0, 1);
        sort(0, 0, 1, 0);
        sort(0, 0, 1, 1);
        sort(0, 1, 0, 0);
        sort(0, 1, 0, 1);
        sort(0, 1, 1, 0);
        sort(0, 1, 1, 1);
        sort(1, 0, 0, 0);
        sort(1, 0, 0, 1);
        sort(1, 0, 1, 0);
        sort(1, 0, 1, 1);
        sort(1, 1, 0, 0);
        sort(1, 1, 0, 1);
        sort(1, 1, 1, 0);
        sort(1, 1, 1, 1);
    }
}
