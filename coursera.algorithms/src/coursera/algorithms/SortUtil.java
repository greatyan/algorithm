package coursera.algorithms;

import java.util.Random;

public class SortUtil {

    static public String[] createRandomStrings(int size) {
        Random r = new Random();
        String[] values = new String[size];
        for (int i = 0; i < size; i++) {
            values[i] = String.valueOf(r.nextInt(size));
        }
        return values;
    }

    static public boolean isSorted(String[] values) {
        for (int i = 1; i < values.length; i++) {
            if (values[i].compareTo(values[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }

    static public void printValues(String[] values) {
        for (String v : values) {
            System.out.println(v);
        }
    }
}
