package com.actuate.wyan;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

public class ContainerWithMostWater {
    @Test
    public void test() {
        Assert.assertEquals(2, new Solution().maxArea(new int[] { 1, 2, 1 }));
        Assert.assertEquals(1, new Solution().maxArea(new int[] { 2, 1 }));
        Assert.assertEquals(9, new Solution().maxArea(new int[] { 3, 2, 1, 3 }));
    }

    public class Solution {

        public int maxArea(int[] height) {
            return maxAreaWithMiddleHigh(height);
        }

        private class Edge {
            Edge(int pos, int height) {
                this.pos = pos;
                this.height = height;
            }

            int pos;
            int height;

            public String toString() {
                return "(" + pos + "," + height + ")";
            }
        }

        Edge[] createEdges(int[] height) {
            Edge[] edges = new Edge[height.length];
            for (int i = 0; i < height.length; i++) {
                edges[i] = new Edge(i, height[i]);
            }
            return edges;
        }

        Edge[] sortEdges(Edge[] edges) {
            Arrays.sort(edges, new Comparator<Edge>() {
                public int compare(Edge e1, Edge e2) {
                    if (e1.height < e2.height) {
                        return -1;
                    }
                    if (e1.height == e2.height) {
                        return 0;
                    }
                    return 1;
                }
            });
            return edges;
        }

        protected int maxAreaWithMiddleHigh(int[] height) {
            if (height == null || height.length <= 1) {
                return 0;
            }
            Edge[] edges = createEdges(height);
            sortEdges(edges);
            int start = Integer.MAX_VALUE;
            int end = Integer.MIN_VALUE;
            int maxValue = 0;
            for (int i = edges.length - 1; i >= 0; i--) {
                Edge edge = edges[i];
                if (edge.pos < start) {
                    start = edge.pos;
                }
                if (edge.pos > end) {
                    end = edge.pos;
                }
                int v = (end - start) * edge.height;
                maxValue = Math.max(v, maxValue);
            }
            return maxValue;
        }

        protected int maxAreaWithOutMiddleHigh(int[] height) {
            if (height == null || height.length <= 1) {
                return 0;
            }
            LinkedList<int[]> stacks = new LinkedList<int[]>();
            int maxValue = 0;
            stacks.add(new int[] { 0, height[0] });
            for (int i = 1; i < height.length; i++) {
                int[] lastEdge = stacks.getLast();
                if (height[i] < lastEdge[1]) {
                    int newContainer = (i - lastEdge[0]) * height[i];
                    maxValue = Math.max(newContainer, maxValue);
                } else {
                    // use the new edge to create a new container
                    int newContainer = (i - lastEdge[0]) * lastEdge[1];
                    maxValue = Math.max(newContainer, maxValue);
                    // pop the previous edges until we find a edge taller than
                    // us
                    while (!stacks.isEmpty()) {
                        int[] edge = stacks.getLast();
                        if (edge[1] < height[i]) {
                            // previous edge is smaller than new one, remove it
                            // out.
                            stacks.removeLast();
                            continue;
                        }
                        // the previous edge is taller or equal with new one.
                        break;
                    }

                    // now we create a new container
                    if (!stacks.isEmpty()) {
                        lastEdge = stacks.getLast();
                        newContainer = (i - lastEdge[0]) * height[i];
                        maxValue = Math.max(newContainer, maxValue);
                    }
                }
                stacks.add(new int[] { i, height[i] });
            }

            // remove edge one by one to create a new container.
            int[] lastEdge = stacks.removeLast();
            while (!stacks.isEmpty()) {
                // the previous edge is larger or equals to last edge
                // remove the edge which is equals to my self.
                int[] prevEdge = stacks.removeLast();
                if (prevEdge[1] == lastEdge[1]) {
                    // create a new container
                    int newContainer = (lastEdge[0] - prevEdge[0])
                            * lastEdge[1];
                    maxValue = Math.max(newContainer, maxValue);

                } else {
                    int newContainer = (lastEdge[0] - prevEdge[0])
                            * lastEdge[1];
                    maxValue = Math.max(newContainer, maxValue);
                    lastEdge = prevEdge;
                }
            }

            return maxValue;
        }
    }
}
