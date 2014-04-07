package com.actuate.wyan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

public class CombinationSumII {

    @Test
    public void test() {
        assertEquals("[1]",
                new Solution().combinationSum2(
                        new int[] { 1 }, 1));
        assertEquals("[1,1,6],[1,2,5],[1,7],[2,6]",
                new Solution().combinationSum2(
                        new int[] { 10, 1, 2, 7, 6, 1, 5 }, 8));
    }

    private void assertEquals(String result, ArrayList<ArrayList<Integer>> r2) {
        StringBuilder sb = new StringBuilder();
        if (r2 != null) {
            for (ArrayList<Integer> v : r2) {
                sb.append("[");
                for (int i : v) {
                    sb.append(i);
                    sb.append(',');
                }
                if (sb.length() > 0) {
                    sb.setLength(sb.length() - 1);
                }
                sb.append("],");
            }
            if (sb.length() > 0) {
                sb.setLength(sb.length() - 1);
            }
        }
        Assert.assertEquals(result, sb.toString());
    }

    public class Solution {
        public ArrayList<ArrayList<Integer>> combinationSum2(int[] num,
                int target) {
            return new BF().combinationSum2(num, target);
        }

        private class Sort {
            public ArrayList<ArrayList<Integer>> combinationSum2(int[] num,
                    int target) {
                Arrays.sort(num);
                ArrayList<ArrayList<Integer>> result = combinate(num, 0, target);
                return merge(result);
            }

            private ArrayList<ArrayList<Integer>> combinate(int[] num,
                    int offset, int target) {

                ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

                if (target == 0) {
                    ArrayList<Integer> values = new ArrayList<Integer>();
                    result.add(values);
                    return result;
                }

                if (target < 0) {
                    return null;
                }

                if (offset >= num.length) {
                    return null;
                }

                ArrayList<ArrayList<Integer>> result1 = combinate(num,
                        offset + 1, target - num[offset]);
                if (result1 != null) {
                    for (ArrayList<Integer> values : result1) {
                        values.add(num[offset]);
                    }
                    result.addAll(result1);
                }
                ArrayList<ArrayList<Integer>> result2 = combinate(num,
                        offset + 1, target);
                if (result2 != null) {
                    result.addAll(result2);
                }
                return result;
            }
        }

        private class BF {
            public ArrayList<ArrayList<Integer>> combinationSum2(int[] num,
                    int target) {
                ArrayList<ArrayList<Integer>> r = combinate(num, 0, target);
                return merge(r);
            }

            private ArrayList<ArrayList<Integer>> combinate(int[] num,
                    int offset, int target) {

                ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
                if (target < 0) {
                    return null;
                }

                if (target == 0) {
                    ArrayList<Integer> values = new ArrayList<Integer>();
                    result.add(values);
                    return result;
                }

                if (offset >= num.length) {
                    return null;
                }

                ArrayList<ArrayList<Integer>> result1 = combinate(num,
                        offset + 1, target - num[offset]);
                if (result1 != null) {
                    for (ArrayList<Integer> values : result1) {
                        values.add(num[offset]);
                    }
                    result.addAll(result1);
                }
                ArrayList<ArrayList<Integer>> result2 = combinate(num,
                        offset + 1, target);
                if (result2 != null) {
                    result.addAll(result2);
                }
                return result;
            }

        }

        private ArrayList<ArrayList<Integer>> merge(
                ArrayList<ArrayList<Integer>> r) {

            HashMap<String, ArrayList<Integer>> set = new HashMap<String, ArrayList<Integer>>();
            if (r != null) {
                for (ArrayList<Integer> v : r) {
                    Collections.sort(v);
                    String key = toString(v);
                    set.put(key, v);
                }
            }

            ArrayList<String> keySet = new ArrayList<String>(set.keySet());
            Collections.sort(keySet);
            ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
            for (String key : keySet) {
                result.add(set.get(key));
            }
            return result;
        }

        private String toString(ArrayList<Integer> r) {
            StringBuilder sb = new StringBuilder();
            for (Integer i : r) {
                sb.append(i);
                sb.append(",");
            }
            return sb.toString();
        }
    }
}
